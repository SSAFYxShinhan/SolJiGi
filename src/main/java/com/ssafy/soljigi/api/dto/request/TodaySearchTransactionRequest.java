package com.ssafy.soljigi.api.dto.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TodaySearchTransactionRequest {

	private DataHeader dataHeader;
	private DataBody dataBody;

	@Data
	public static class DataHeader {
		private String apikey;
	}

	@Data
	public static class DataBody {
		@JsonProperty("계좌번호")
		private String accountNumber;
		@JsonProperty("검색날짜")
		private LocalDateTime dateTime;
	}
}
