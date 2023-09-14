package com.ssafy.soljigi.diagnosis.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.soljigi.diagnosis.service.AttentionService;
import com.ssafy.soljigi.diagnosis.service.ExecutiveService;
import com.ssafy.soljigi.diagnosis.service.LanguageService;
import com.ssafy.soljigi.diagnosis.service.MemoryService;
import com.ssafy.soljigi.diagnosis.service.OrientService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/diagnosis")
@RequiredArgsConstructor
public class DiagnosisController {

	private final OrientService orientService;
	private final MemoryService memoryService;
	private final AttentionService attentionService;
	// private final SpacetimeService spacetimeService;
	 private final ExecutiveService executiveService;
	private final LanguageService languageService;

	@GetMapping
	public String diagnosis(Model model) {
		Map<String, String> memory4w1h = memoryService.getQuiz4W1H();
		model.addAttribute("orient", orientService.getQuiz());
		model.addAttribute("memory4w1h", memory4w1h);
		model.addAttribute("memory", memoryService.getQuiz(memory4w1h));
		model.addAttribute("attention", attentionService.getQuiz());
		model.addAttribute("spacetime", -1);
		model.addAttribute("executiveFluency", executiveService.getFluencyQuiz());
		model.addAttribute("executiveVirtual", executiveService.getVisualQuiz());
		model.addAttribute("language", languageService.getQuiz());
		return "diagnosis/diagnosis";
	}

}
