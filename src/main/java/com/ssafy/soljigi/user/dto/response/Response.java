package com.ssafy.soljigi.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Response<T> {
	private String resultCode;
	private T result;

	//에러 리턴
	public static Response<ErrorResponse> error(ErrorResponse errorResponse) {
		return new Response("ERROR", errorResponse);
	}

	// 성공 리턴
	public static <T> Response<T> success(T result) {
		return new Response("SUCCESS", result);
	}
}
