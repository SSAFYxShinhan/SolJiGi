package com.ssafy.soljigi.user.error;

public class AppException extends RuntimeException {

	ErrorCode errorCode;

	public AppException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
