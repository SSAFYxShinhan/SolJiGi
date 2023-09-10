package com.ssafy.soljigi.game.gpt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatGptRes {
	private String responseMessage;
	private String category;
}
