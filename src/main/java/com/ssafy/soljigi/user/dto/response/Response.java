package com.ssafy.soljigi.user.dto.response;

import com.ssafy.soljigi.base.error.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Response<T> {
	private String resultCode;
	private T result;

	//에러 리턴
	public static Response<ErrorResponse> error(ErrorCode errorCode) {
		return new Response(errorCode.getHttpStatus().toString(), errorCode.getMessage());
	}

	// 성공 리턴
	public static <T> Response<T> success(T result) {
		return new Response("SUCCESS", result);
	}
}
