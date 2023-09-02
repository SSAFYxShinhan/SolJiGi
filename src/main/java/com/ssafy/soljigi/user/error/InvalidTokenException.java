package com.ssafy.soljigi.user.error;

public class InvalidTokenException extends AppException {
	public InvalidTokenException() {
		super(ErrorCode.INVALID_TOKEN);
	}
}
