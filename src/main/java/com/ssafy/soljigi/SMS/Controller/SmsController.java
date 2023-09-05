package com.ssafy.soljigi.SMS.Controller;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.soljigi.SMS.DTO.MessageDTO;
import com.ssafy.soljigi.SMS.DTO.SmsResponseDTO;
import com.ssafy.soljigi.SMS.Service.SmsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SmsController {

	// private final SmsService smsService;
	//
	// @GetMapping("/send")
	// public String getSmsPage() {
	// 	return "SMS/sendSms";
	// }
	//
	// @PostMapping("/sms/send")
	// public String sendSms(MessageDTO messageDto, Model model) throws
	// 	JsonProcessingException,
	// 	RestClientException,
	// 	URISyntaxException, InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
	// 	messageDto.setTo("01088373012");
	// 	messageDto.setContent("신한은행 해커톤 SMS 테스트"); 
	// 	SmsResponseDTO response = smsService.sendSms(messageDto);
	// 	model.addAttribute("response", response);
	// 	return "SMS/result";
	// } 
}