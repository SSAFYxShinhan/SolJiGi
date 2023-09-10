package com.ssafy.soljigi.game.service;

import org.springframework.stereotype.Service;

import com.ssafy.soljigi.base.error.BaseException;
import com.ssafy.soljigi.base.error.BaseResponseStatus;
import com.ssafy.soljigi.game.dto.ChatGptRes;

import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ChatGPTService {

	private ChatgptService chatgptService;

	public ChatGptRes getChatResponse(String prompt) throws BaseException {
		try {
			// ChatGPT 에게 질문을 던집니다.
			String responseMessage = chatgptService.sendMessage(prompt);
			return new ChatGptRes(responseMessage);
		} catch (Exception exception) {
			throw new BaseException(BaseResponseStatus.SERVER_ERROR);
		}
	}
}