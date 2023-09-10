package com.ssafy.soljigi.dementiaTest.controller;

import java.util.HashMap;

import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.soljigi.dementiaTest.dto.SaveDTO;
import com.ssafy.soljigi.dementiaTest.service.LanguageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class TestController {
	@GetMapping("/test")
	public String getSmsPage() {
		return "demenTest/demenResult";
	}

	@GetMapping("/gettest")
	public HashMap<String, Object> getQuizzes() {
		HashMap<String, Object> map = new ManagedMap<>();
		map = LanguageService.getQuiz(map);

		// map 내용 출력
		// map.forEach((key, value) -> {
		// 	System.out.println(key + " : " + value);
		// });

		return map;
	}

	@ResponseBody
	@PostMapping("test/result")
	public SaveDTO save(@RequestBody SaveDTO saveDTO) {
		System.out.println(saveDTO.getOrient() + " " + saveDTO.getAttention());
		return saveDTO;
	}

}