package com.ssafy.soljigi.user.error.exception;

import com.ssafy.soljigi.user.error.AppException;
import com.ssafy.soljigi.user.error.ErrorCode;

public class InvalidTokenException extends AppException {
	public InvalidTokenException() {
		super(ErrorCode.INVALID_TOKEN);
	}
}
