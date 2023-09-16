package com.ssafy.soljigi.user.dto.response;

import com.ssafy.soljigi.base.error.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {
	private final ErrorCode errorCode;
	private final String message;
}