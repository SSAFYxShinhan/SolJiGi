package com.ssafy.soljigi.dementiaTest.controller;

import java.util.HashMap;

import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.soljigi.dementiaTest.service.AttentionService;
import com.ssafy.soljigi.dementiaTest.service.MemoryService;
import com.ssafy.soljigi.dementiaTest.service.OrientService;
import com.ssafy.soljigi.dementiaTest.service.TestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class TestController {
	
	private final TestService testService;

	@GetMapping("/gettest")
	public HashMap<String, Object> getQuizzes() {
		HashMap<String, Object> map = new ManagedMap<>();
		map = OrientService.getQuiz(map);
		map = MemoryService.getQuiz(map);
		map = AttentionService.getQuiz(map);

		// map 내용 출력
		// map.forEach((key, value) -> {
		// 	System.out.println(key + " : " + value);
		// });

		return map;
	}

}