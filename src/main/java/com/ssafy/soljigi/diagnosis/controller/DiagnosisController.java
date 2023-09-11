package com.ssafy.soljigi.diagnosis.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.soljigi.diagnosis.dto.ResultSaveDTO;
import com.ssafy.soljigi.diagnosis.service.AttentionService;
import com.ssafy.soljigi.diagnosis.service.ExecutiveService;
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

	@GetMapping
	public String diagnosis(Model model) {
		Map<String, String> memory4w1h = memoryService.getQuiz4W1H();
		model.addAttribute("orient", orientService.getQuiz());
		model.addAttribute("memory4w1h", memory4w1h);
		model.addAttribute("memory", memoryService.getQuiz(memory4w1h));
		model.addAttribute("attention", attentionService.getQuiz());
		model.addAttribute("spacetime", -1);
		model.addAttribute("executive", executiveService.getQuiz());

		return "diagnosis/diagnosis";
	}

	@GetMapping("/diagnosis/resultPage")
	public String getDiagnosisPage() {
		return "diagnosis/diagnosisResult";
	}

	@ResponseBody
	@PostMapping("diagnosis/saveResult")
	public ResultSaveDTO save(@RequestBody ResultSaveDTO saveDTO) {
		System.out.println(saveDTO.getOrient() + " " + saveDTO.getAttention());
		return saveDTO;
	}
}
