package com.ssafy.soljigi.user.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager {
	@ExceptionHandler(AppException.class)
	public ResponseEntity<?> appExceptionHandler(AppException e) {
		return ResponseEntity.status(e.errorCode.getHttpStatus())
			.body(e.errorCode.getMessage());
	}

}
