package com.ssafy.soljigi.user.error;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {

	ErrorCode errorCode;

	public AppException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
