package com.ssafy.soljigi.base.error.exception;

import static com.ssafy.soljigi.base.error.ErrorCode.*;

import com.ssafy.soljigi.base.error.AppException;

public class UserNotLoggedInException extends AppException {
	public UserNotLoggedInException() {
		super(USER_NOT_LOGGED_IN);
	}
}