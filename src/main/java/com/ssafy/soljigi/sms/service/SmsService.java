//package com.ssafy.soljigi.sms.service;
//
//import java.io.UnsupportedEncodingException;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.nio.charset.StandardCharsets;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;
//
//import org.apache.tomcat.util.codec.binary.Base64;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestClientException;
//import org.springframework.web.client.RestTemplate;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.ssafy.soljigi.diagnosis.dto.request.DiagnosisResultSaveRequest;
//import com.ssafy.soljigi.sms.dto.MessageDTO;
//import com.ssafy.soljigi.sms.dto.SmsRequestDTO;
//import com.ssafy.soljigi.sms.dto.SmsResponseDTO;
//import com.ssafy.soljigi.user.entity.User;
//import com.ssafy.soljigi.user.repository.UserRepository;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@RequiredArgsConstructor
//@Service
//public class SmsService {
//	@Value("${naver-cloud-sms.accessKey}")
//	private String accessKey;
//
//	@Value("${naver-cloud-sms.secretKey}")
//	private String secretKey;
//
//	@Value("${naver-cloud-sms.serviceId}")
//	private String serviceId;
//
//	@Value("${naver-cloud-sms.senderPhone}")
//	private String phone;
//
//	private final UserRepository userRepository;
//
//	public SmsResponseDTO sendDiagnosticResult(Long userId, String content) {
//		User user = userRepository.findById(userId)
//			.orElseThrow(IllegalArgumentException::new);
//
//		// String phoneNumber = user.getCareGiverNumber();
//		String phoneNumber = "01089258114";
//		SmsResponseDTO response;
//		try {
//			response = sendSms(new MessageDTO(phoneNumber, content));
// 		} catch (UnsupportedEncodingException | URISyntaxException | NoSuchAlgorithmException | InvalidKeyException |
//				 JsonProcessingException e) {
//			// throw new RuntimeException(e);
//			return null;
//		}
//		return response;
//	}
//
//	public SmsResponseDTO sendSms(MessageDTO messageDto) throws
//		JsonProcessingException,
//		RestClientException,
//		URISyntaxException, InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
//
//		Long time = System.currentTimeMillis();
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		headers.set("x-ncp-apigw-timestamp", time.toString());
//		headers.set("x-ncp-iam-access-key", accessKey);
//		headers.set("x-ncp-apigw-signature-v2", makeSignature(time));
//
//		List<MessageDTO> messages = new ArrayList<>();
//		messages.add(messageDto);
//
//		SmsRequestDTO request = SmsRequestDTO.builder()
//			.type("sms")
//			.contentType("COMM")
//			.countryCode("82")
//			.from(phone)
//			.content(messageDto.getContent())
//			.messages(messages)
//			.build();
//
//		ObjectMapper objectMapper = new ObjectMapper();
//		String body = objectMapper.writeValueAsString(request);
//		HttpEntity<String> httpBody = new HttpEntity<>(body, headers);
//
//		RestTemplate restTemplate = new RestTemplate();
//		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
//		SmsResponseDTO response = restTemplate.postForObject(new URI("https://sens.apigw.ntruss.com/sms/v2/services/"+ serviceId +"/messages"), httpBody, SmsResponseDTO.class);
//
//		return response;
//	}
//
//	private String makeSignature(Long time) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
//		String space = " ";
//		String newLine = "\n";
//		String method = "POST";
//		String url = "/sms/v2/services/"+ this.serviceId+"/messages";
//		String timestamp = time.toString();
//		String accessKey = this.accessKey;
//		String secretKey = this.secretKey;
//
//		String message = method
//			+ space
//			+ url
//			+ newLine
//			+ timestamp
//			+ newLine
//			+ accessKey;
//
//		SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
//		Mac mac = Mac.getInstance("HmacSHA256");
//		mac.init(signingKey);
//
//		byte[] rawHmac = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
//		return Base64.encodeBase64String(rawHmac);
//	}
//}
