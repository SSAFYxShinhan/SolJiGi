package com.ssafy.soljigi.user.error.exception;

import static com.ssafy.soljigi.user.error.ErrorCode.*;

import com.ssafy.soljigi.user.error.AppException;

public class UserNotLoggedInException extends AppException {
	public UserNotLoggedInException() {
		super(USER_NOT_LOGGED_IN);
	}
}