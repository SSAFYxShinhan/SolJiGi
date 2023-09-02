package com.ssafy.soljigi.dementiaTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DementiaTest {
	static String userName = "김싸피";
	static int randNum;

	// 테스트용 main문 입니다
	// public static void main(String[] args) {
	// 	Random random = new Random();
	// 	for (int i = 0; i < 20; i++) {
	// 		System.out.println(random.nextInt(4));
	// 	}
	// }

	@RequestMapping(value = "/test")
	public String testStartPage(Model model) {
		model.addAttribute("userName", userName);
		return "dementiaTest";
	}

	@ResponseBody
	@RequestMapping(value = "/getQuiz", method = RequestMethod.POST)
	public HashMap<String, Object> init(@RequestBody HashMap<String, Object> map) {
		Random random = new Random();

		// 현재 날짜 구하기 (시스템 시계, 시스템 타임존)
		LocalDate nowTime = LocalDate.now();
		int year = nowTime.getYear(); // 년, 2023
		int monthValue = nowTime.getMonthValue(); // 월, 6
		int dayOfMonth = nowTime.getDayOfMonth(); // 일, 17
		int dayOfWeekValue = nowTime.getDayOfWeek().getValue(); // 요일, 4

		// Q1 : 올해 년도 맞추기
		ArrayList<String> quiz1 = new ArrayList<>();
		randNum = random.nextInt(4);
		year -= randNum;
		quiz1.add(Integer.toString(year)); // option 1
		quiz1.add(Integer.toString(year++)); // option 2
		quiz1.add(Integer.toString(year++)); // option 3
		quiz1.add(Integer.toString(year++)); // option 4
		quiz1.add(Integer.toString(year)); // answer
		map.put("q1", quiz1);

		return map;
	}

	@RequestMapping(value = "/tester")
	public String dementiaTester(Model model) {
		return "dementiaTester";
	}

}