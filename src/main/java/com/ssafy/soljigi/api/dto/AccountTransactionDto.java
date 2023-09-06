package com.ssafy.soljigi.api.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.ssafy.soljigi.api.entity.Account;
import com.ssafy.soljigi.api.entity.Transaction;

import lombok.Data;

@Data
public class AccountTransactionDto {

	private DataHeader dataHeader;
	private DataBody dataBody;

	@Data
	public static class DataHeader {
		private int successCode;
		private int resultCode;
		private String resultMessage;
	}

	@Data
	public static class DataBody {
		private String accountNumber;
		private String productName;
		private long balance;
		private String customerName;
		private int transactionCount;
		private List<TransactionDetail> transactionDetails;

		@Data
		public static class TransactionDetail {
			private String transactionDate;
			private String transactionTime;
			private String summary;
			private long withdraw;
			private long deposit;
			private String content;
			private long balance;
			private int inOutType;
			private String branchName;
		}
	}

	public static AccountTransactionDto of(Account account) {
		AccountTransactionDto response = new AccountTransactionDto();

		AccountTransactionDto.DataHeader header = new AccountTransactionDto.DataHeader();
		header.setSuccessCode(0);
		header.setResultCode(0);
		header.setResultMessage("Success");

		AccountTransactionDto.DataBody body = new AccountTransactionDto.DataBody();
		body.setAccountNumber(account.getAccountNumber());
		body.setProductName(account.getProductName());
		body.setBalance(account.getBalance());
		body.setCustomerName(account.getCustomerName());

		List<Transaction> transactions = account.getTransactions();
		body.setTransactionCount(transactions.size());
		body.setTransactionDetails(transactions.stream()
			.map(transaction -> {
				DataBody.TransactionDetail detail = new DataBody.TransactionDetail();
				detail.setTransactionDate(transaction.getTransactionDate());
				detail.setTransactionTime(transaction.getTransactionTime());
				detail.setSummary(transaction.getSummary());
				detail.setWithdraw(transaction.getWithdraw());
				detail.setDeposit(transaction.getDeposit());
				detail.setContent(transaction.getContent());
				detail.setBalance(transaction.getBalance());
				detail.setInOutType(transaction.getInOutType());
				detail.setBranchName(transaction.getBranchName());
				return detail;
			})
			.collect(Collectors.toList()));

		response.setDataHeader(header);
		response.setDataBody(body);
		return response;
	}
}
