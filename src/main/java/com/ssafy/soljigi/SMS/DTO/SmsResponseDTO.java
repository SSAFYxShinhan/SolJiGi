package com.ssafy.soljigi.SMS.DTO;

import java.time.LocalDateTime;
import java.util.List;

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