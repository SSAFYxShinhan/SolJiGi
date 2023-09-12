package com.ssafy.soljigi.api.dto.response;

import lombok.Data;

@Data
public class OneTransferResponse {

	private ResponseDataHeader dataHeader;
	private DataBody dataBody;

	@Data
	public static class DataBody {
		private String 입금은행코드;
		private String 입금계좌번호;
	}

	public static OneTransferResponse of(String bankCode, String accountNumber) {
		OneTransferResponse response = new OneTransferResponse();
		response.setDataHeader(ResponseDataHeader.ofSuccess());

		DataBody dataBody = new DataBody();
		dataBody.입금은행코드 = bankCode;
		dataBody.입금계좌번호 = accountNumber;
		response.setDataBody(dataBody);

		return response;
	}

	public static OneTransferResponse ofFail() {
		OneTransferResponse response = new OneTransferResponse();
		response.setDataHeader(ResponseDataHeader.ofFail());
		return response;
	}

	public static OneTransferResponse ofFail(String message) {
		OneTransferResponse response = new OneTransferResponse();
		response.setDataHeader(ResponseDataHeader.ofFail(message));
		return response;
	}
}
