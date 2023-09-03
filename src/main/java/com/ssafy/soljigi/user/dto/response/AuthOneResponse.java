package com.ssafy.soljigi.user.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthOneResponse {
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
		@JsonProperty("입금은행코드")
		private String bankCode;
		@JsonProperty("입금계좌번호")
		private String accountNumber;
	}
}
