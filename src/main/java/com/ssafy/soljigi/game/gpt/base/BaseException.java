package com.ssafy.soljigi.game.gpt.base;

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