package com.ssafy.soljigi.user.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ssafy.soljigi.user.dto.response.AuthOneResponse;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class AuthOneService {

	public AuthOneResponse authOne(String accountNumber) {
		RestTemplate restTemplate = new RestTemplate();
		String authOneKey = "1234 SSAFY";
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("dataHeader", Map.of("apikey", "2023_Shinhan_SSAFY_Hackathon"));
		requestBody.put("dataBody", Map.of("입금은행코드", "008", "입금계좌번호", accountNumber, "입금통장메모", authOneKey));

		final String AUTH_ONE_URL = "https://shbhack.shinhan.com/v1/auth/1transfer";

		AuthOneResponse response = restTemplate.postForObject(AUTH_ONE_URL, requestBody, AuthOneResponse.class);

		if (response.getDataHeader().getSuccessCode() == 1) {
			// throw new AppException();
			return null;
		}

		return AuthOneResponse.builder()
			.dataHeader(response.getDataHeader())
			.dataBody(response.getDataBody())
			.build();
	}
}
