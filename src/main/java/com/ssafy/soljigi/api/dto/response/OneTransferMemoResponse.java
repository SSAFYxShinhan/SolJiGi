package com.ssafy.soljigi.api.dto.response;

import lombok.Data;

@Data
public class OneTransferMemoResponse {

	private ResponseDataHeader dataHeader;
	private DataBody dataBody;

	@Data
	public static class DataBody {
		private String 입금통장메모;
	}

	public static OneTransferMemoResponse of(String memo) {
		OneTransferMemoResponse response = new OneTransferMemoResponse();
		response.setDataHeader(ResponseDataHeader.ofSuccess());

		DataBody dataBody = new DataBody();
		dataBody.입금통장메모 = memo;
		response.setDataBody(dataBody);

		return response;
	}

	public static OneTransferMemoResponse ofFail(String message) {
		OneTransferMemoResponse response = new OneTransferMemoResponse();
		response.setDataHeader(ResponseDataHeader.ofFail(message));
		return response;
	}
}
