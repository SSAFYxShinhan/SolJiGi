package com.ssafy.soljigi.api.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.ssafy.soljigi.api.entity.Account;
import com.ssafy.soljigi.api.entity.Transaction;

import lombok.Data;

@Data
public class AccountTransactionResponse {

	private ResponseDataHeader dataHeader;
	private DataBody dataBody;

	@Data
	public static class DataBody {
		private String 계좌번호;
		private String 상품명;
		private long 계좌잔액;
		private String 고객명;
		private int 거래내역반복횟수;
		private List<TransactionDetail> 거래내역;

		@Data
		public static class TransactionDetail {
			private String 거래일자;
			private String 거래시간;
			private String 적요;
			private long 출금금액;
			private long 입금금액;
			private String 내용;
			private long 잔액;
			private int 입지구분;
			private String 거래점명;
		}
	}

	public static AccountTransactionResponse of(Account account) {
		AccountTransactionResponse response = new AccountTransactionResponse();

		ResponseDataHeader dataHeader = ResponseDataHeader.ofSuccess();

		AccountTransactionResponse.DataBody dataBody = new AccountTransactionResponse.DataBody();
		dataBody.set계좌번호(account.getAccountNumber());
		dataBody.set상품명(account.getProductName());
		dataBody.set계좌잔액(account.getBalance());
		dataBody.set고객명(account.getCustomerName());

		List<Transaction> transactions = account.getTransactions().stream()
			.sorted((o1, o2) -> o1.getTransactionDateTime().compareTo(o2.getTransactionDateTime()) * -1).toList();
		dataBody.set거래내역반복횟수(transactions.size());
		dataBody.set거래내역(transactions.stream()
			.map(transaction -> {
				DataBody.TransactionDetail detail = new DataBody.TransactionDetail();
				detail.set거래일자(transaction.getTransactionDate());
				detail.set거래시간(transaction.getTransactionTime());
				detail.set적요(transaction.getSummary());
				detail.set출금금액(transaction.getWithdraw());
				detail.set입금금액(transaction.getDeposit());
				detail.set내용(transaction.getContent());
				detail.set잔액(transaction.getBalance());
				detail.set입지구분(transaction.getInOutType());
				detail.set거래점명(transaction.getBranchName());
				return detail;
			})
			.collect(Collectors.toList()));

		response.setDataHeader(dataHeader);
		response.setDataBody(dataBody);
		return response;
	}

	public static AccountTransactionResponse ofFail(String message) {
		AccountTransactionResponse response = new AccountTransactionResponse();
		response.setDataHeader(ResponseDataHeader.ofFail(message));
		response.setDataBody(null);
		return response;
	}
}
