package com.ssafy.soljigi.base.error;

public class BaseException extends Exception {
	private final BaseResponseStatus status;

	public BaseException(BaseResponseStatus status) {
		super(status.getMessage());
		this.status = status;
	}

	public BaseResponseStatus getStatus() {
		return status;
	}
}