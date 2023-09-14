package com.ssafy.soljigi.user.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ssafy.soljigi.base.error.AppException;
import com.ssafy.soljigi.base.error.ErrorCode;
import com.ssafy.soljigi.game.dto.response.TransactionResponse;
import com.ssafy.soljigi.user.dto.response.Response;
import com.ssafy.soljigi.user.entity.User;
import com.ssafy.soljigi.user.repository.UserRepository;
import com.ssafy.soljigi.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	private final String TRANSACTION_URL = "http://localhost:8080";

	public User findByUsername(String username) {
		return userRepository.findByUsername(username)
			.orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
	}

	public TransactionResponse findByTransactionCount(String username) {
		User user = userRepository.findByUsername(username)
			.orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
		String accountNumber = user.getAccountNumber();

		// API 요청에 필요한 데이터 설정 (예시에 따른 요청 본문)
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("dataHeader", Map.of("apikey", "2023_Shinhan_SSAFY_Hackathon"));
		requestBody.put("dataBody", Map.of("계좌번호", accountNumber));

		// API 호출 및 응답 받기
		RestTemplate restTemplate = new RestTemplate();
		TransactionResponse response = restTemplate.postForObject(TRANSACTION_URL + "/v1/search/transaction",
			requestBody,
			TransactionResponse.class);

		if (response.getDataHeader().getSuccessCode() == 1) {
			throw new IllegalArgumentException();
		}

		return response;
	}

	public Response<?> findPaymentPatternByTransaction(String username) {
		User user = userRepository.findByUsername(username)
			.orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
		String accountNumber = user.getAccountNumber();

		// API 요청에 필요한 데이터 설정 (예시에 따른 요청 본문)
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("dataHeader", Map.of("apikey", "2023_Shinhan_SSAFY_Hackathon"));
		requestBody.put("dataBody", Map.of("계좌번호", accountNumber));

		// API 호출 및 응답 받기
		RestTemplate restTemplate = new RestTemplate();
		TransactionResponse response = restTemplate.postForObject(TRANSACTION_URL + "/v1/search/transaction",
			requestBody,
			TransactionResponse.class);

		if (response.getDataHeader().getSuccessCode() == 1) {
			throw new IllegalArgumentException();
		}
		List<String> contentList = response.getDataBody()
			.getTransactionDetails()
			.stream()
			.filter(e -> e.getContent() != null)
			.map(e -> e.getContent())
			.toList();

		HashMap<String, Integer> map = new HashMap<>();
		for (String content : contentList) {
			if (map.containsKey(content)) {
				map.put(content, map.get(content) + 1);
			} else {
				map.put(content, 1);
			}
		}

		ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
		entries.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
		Map<String, Integer> result = new HashMap<>();
		for (int i = 0; i < 5; i++) {
			result.put(entries.get(i).getKey(), entries.get(i).getValue());
		}
		return Response.success(result);
	}

	@Override
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) {
				return userRepository.findByUsername(username)
					.orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
			}
		};
	}
}
