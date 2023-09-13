package com.ssafy.soljigi.api.dto.response;

import lombok.Data;

@Data
public class ResponseDataHeader {

	private static final String SUCCESS_CODE = "0";
	private static final String FAIL_CODE = "1";

	private String successCode;
	private String resultCode;
	private String resultMessage;

	public static ResponseDataHeader ofSuccess() {
		ResponseDataHeader header = new ResponseDataHeader();
		header.setSuccessCode(SUCCESS_CODE);
		header.setResultCode("");
		header.setResultMessage("");
		return header;
	}

	public static ResponseDataHeader ofFail() {
		ResponseDataHeader header = new ResponseDataHeader();
		header.setSuccessCode(FAIL_CODE);
		header.setResultCode(FAIL_CODE);
		header.setResultMessage(null);
		return header;
	}

	public static ResponseDataHeader ofFail(String resultMessage) {
		ResponseDataHeader header = new ResponseDataHeader();
		header.setSuccessCode(FAIL_CODE);
		header.setResultCode(FAIL_CODE);
		header.setResultMessage(resultMessage);
		return header;
	}
}
