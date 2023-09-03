package com.ssafy.soljigi.game.transaction.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TransactionResponse {

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
		@JsonProperty("계좌번호")
		private String accountNumber;
		@JsonProperty("상품명")
		private String productName;
		@JsonProperty("계좌잔액")
		private long balance;
		@JsonProperty("고객명")
		private String customerName;
		@JsonProperty("거래내역반복횟수")
		private int transactionCount;
		@JsonProperty("거래내역")
		private List<TransactionDetail> transactionDetails;

		@Data
		public static class TransactionDetail {
			@JsonProperty("거래일자")
			private String transactionDate;
			@JsonProperty("거래시간")
			private String transactionTime;
			@JsonProperty("적요")
			private String summary;
			@JsonProperty("출금금액")
			private long withdrawalAmount;
			@JsonProperty("입금금액")
			private long depositAmount;
			@JsonProperty("내용")
			private String content;
			@JsonProperty("잔액")
			private long balance;
			@JsonProperty("입지구분")
			private int inOutType;
			@JsonProperty("거래점명")
			private String branchName;
		}
	}
}
