package com.ssafy.soljigi.base.api.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {

	private static final String SUCCESS_STATUS = "success";
	private static final String FAIL_STATUS = "fail";
	private static final String ERROR_STATUS = "error";

	private String status;
	private T data;
	private String message;

	public static <T> ApiResponse<T> ofSuccess(T data) {
		return new ApiResponse<>(SUCCESS_STATUS, data, null);
	}

	public static <T> ApiResponse<T> ofSuccess() {
		return new ApiResponse<>(SUCCESS_STATUS, null, null);
	}

	public static ApiResponse<?> ofFail(BindingResult bindingResult) {
		Map<String, String> errors = new HashMap<>();

		List<ObjectError> allErrors = bindingResult.getAllErrors();
		for (ObjectError error : allErrors) {
			if (error instanceof FieldError) {
				errors.put(((FieldError) error).getField(), error.getDefaultMessage());
			} else {
				errors.put( error.getObjectName(), error.getDefaultMessage());
			}
		}
		return new ApiResponse<>(FAIL_STATUS, errors, null);
	}

	public static ApiResponse<?> ofError(String message) {
		return new ApiResponse<>(ERROR_STATUS, null, message);
	}
}
