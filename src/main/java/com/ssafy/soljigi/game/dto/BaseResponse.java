package com.ssafy.soljigi.game.dto;

import com.ssafy.soljigi.base.error.BaseResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
	private int statusCode;
	private String message;
	private T data;

	public BaseResponse(T data) {
		this.statusCode = 200; // 성공한 경우 기본 상태 코드는 200입니다.
		this.message = "SUCCESS";
		this.data = data;
	}

	public BaseResponse(BaseResponseStatus status) {
		this.statusCode = status.getStatusCode();
		this.message = status.getMessage();
	}
}