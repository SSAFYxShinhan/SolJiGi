package com.ssafy.soljigi.base.error.exception;

import com.ssafy.soljigi.base.error.AppException;
import com.ssafy.soljigi.base.error.ErrorCode;

public class InvalidTokenException extends AppException {
	public InvalidTokenException() {
		super(ErrorCode.INVALID_TOKEN);
	}
}
