package com.ssafy.soljigi.base.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ssafy.soljigi.user.dto.response.Response;

@RestControllerAdvice
public class ExceptionManager {
	@ExceptionHandler(AppException.class)
	public ResponseEntity<?> appExceptionHandler(AppException e) {
		return ResponseEntity.status(e.errorCode.getHttpStatus())
			.body(ResponseEntity.status(e.errorCode.getHttpStatus()).body(Response.error(e.errorCode)));
	}

}
