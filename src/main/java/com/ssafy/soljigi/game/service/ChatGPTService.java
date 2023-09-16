package com.ssafy.soljigi.game.service;

import org.springframework.stereotype.Service;

import com.ssafy.soljigi.base.error.BaseException;
import com.ssafy.soljigi.base.error.BaseResponseStatus;
import com.ssafy.soljigi.game.dto.response.ChatGptResponse;

import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ChatGPTService {

	private ChatgptService chatgptService;

	public ChatGptResponse getChatResponse(String prompt) throws BaseException {
		try {

			String responseMessage = chatgptService.sendMessage(prompt);
			return new ChatGptResponse(responseMessage);
		} catch (Exception exception) {
			throw new BaseException(BaseResponseStatus.SERVER_ERROR);
		}
	}
}