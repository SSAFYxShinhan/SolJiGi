package com.ssafy.soljigi.sms.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class SmsResponseDTO {
	String requestId;
	LocalDateTime requestTime;
	String statusCode;
	String statusName;
}