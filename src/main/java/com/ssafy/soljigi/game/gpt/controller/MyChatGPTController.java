package com.ssafy.soljigi.game.gpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.soljigi.game.gpt.base.BaseException;
import com.ssafy.soljigi.game.gpt.base.BaseResponse;
import com.ssafy.soljigi.game.gpt.entity.ChatGptReq;
import com.ssafy.soljigi.game.gpt.entity.ChatGptRes;
import com.ssafy.soljigi.game.gpt.service.MyChatGPTService;
import com.ssafy.soljigi.user.service.JwtService;

@RequestMapping("/chatGPT")
@RestController
public class MyChatGPTController {
	String qreQuestion = "안녕, ChatGPT! 나 질문이 있어.";
	private final MyChatGPTService chatGPTService;
	private final JwtService jwtService;

	@Autowired
	public MyChatGPTController(MyChatGPTService chatGPTService, JwtService jwtService) {
		this.chatGPTService = chatGPTService;
		this.jwtService = jwtService;
	}

	@ResponseBody
	@PostMapping("/askChatGPT")
	public BaseResponse<ChatGptRes> askToChatGPT(@RequestBody ChatGptReq chatGptReq) {
		try {
			String resultQuestion = qreQuestion + chatGptReq.getQuestion();
			ChatGptRes chatGptRes = chatGPTService.getChatResponse(resultQuestion);

			return new BaseResponse(chatGptRes);
		} catch (BaseException baseException) {
			return new BaseResponse(baseException.getStatus());
		}
	}
}