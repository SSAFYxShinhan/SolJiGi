package com.ssafy.soljigi.game.gpt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.soljigi.game.gpt.base.BaseException;
import com.ssafy.soljigi.game.gpt.base.BaseResponseStatus;
import com.ssafy.soljigi.game.gpt.entity.ChatGptRes;

import io.github.flashvayne.chatgpt.service.ChatgptService;

@Service
public class MyChatGPTService {
	private ChatgptService chatgptService;

	@Autowired
	public MyChatGPTService(ChatgptService chatgptService) {
		this.chatgptService = chatgptService;
	}

	public ChatGptRes getChatResponse(String prompt) throws BaseException {
		try {
			// ChatGPT 에게 질문을 던집니다.
			String responseMessage = chatgptService.sendMessage(prompt);
			String category = "general";
			return new ChatGptRes(responseMessage, category);
		} catch (Exception exception) {
			throw new BaseException(BaseResponseStatus.SERVER_ERROR);
		}
	}
}