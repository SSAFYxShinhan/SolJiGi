package com.ssafy.soljigi.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OneTransferRequest {

	private DataHeader dataHeader;
	private DataBody dataBody;

	@Data
	public static class DataHeader {
		private String apikey;
	}

	@Data
	public static class DataBody {
		@JsonProperty("입금은행코드")
		private String bankCode;
		@JsonProperty("입금계좌번호")
		private String accountNumber;
		@JsonProperty("입금통장메모")
		private String memo;
	}
}