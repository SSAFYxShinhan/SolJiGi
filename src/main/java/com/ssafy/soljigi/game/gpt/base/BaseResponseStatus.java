package com.ssafy.soljigi.game.gpt.base;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseResponseStatus {
	private int statusCode;
	private String message;

	public static final BaseResponseStatus SERVER_ERROR = new BaseResponseStatus(500, "서버 오류");
}
