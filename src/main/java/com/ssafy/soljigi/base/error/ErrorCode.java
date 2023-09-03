package com.ssafy.soljigi.base.error;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
	DUPLICATED_USERNAME(HttpStatus.CONFLICT, "이미 존재하는 아이디 입니다."),
	FORBIDDEN_REQUEST(HttpStatus.FORBIDDEN, "ADMIN 회원만 접근할 수 있습니다."),
	USER_NOT_LOGGED_IN(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다"),
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자 이름이 없습니다"),
	USER_NOT_PASSWORD(HttpStatus.UNAUTHORIZED, "사용자 비밀번호가 틀렸습니다"),

	INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "토큰이 유효하지 않습니다."),
	EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다."),
	MALFORMED_TOKEN(HttpStatus.UNAUTHORIZED, "잘못된 토큰 형식입니다."),
	NOT_TRUSTED_TOKEN(HttpStatus.UNAUTHORIZED, "신뢰할 수 없는 토큰입니다."),

	UNREACHABLE_SERVICE(HttpStatus.UNAUTHORIZED, "접근 방식이 잘못되었습니다.");
	private HttpStatus httpStatus;
	private String message;
}
