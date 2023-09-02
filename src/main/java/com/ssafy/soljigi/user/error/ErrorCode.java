package com.ssafy.soljigi.user.error;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
	DUPLICATED_USERNAME(HttpStatus.CONFLICT, "이미 존재하는 아이디 입니다.");

	private HttpStatus httpStatus;
	private String message;
}
