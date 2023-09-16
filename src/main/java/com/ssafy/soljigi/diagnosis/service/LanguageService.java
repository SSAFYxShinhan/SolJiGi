package com.ssafy.soljigi.diagnosis.service;

import java.util.List;

import com.ssafy.soljigi.diagnosis.repository.DiagnosisQuizRepository;
import org.springframework.stereotype.Service;

import com.ssafy.soljigi.diagnosis.dto.response.DiagnosisQuizDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LanguageService {

	private final DiagnosisQuizRepository quizRepository;

	public List<DiagnosisQuizDto> getQuiz(int count) {
		return quizRepository.findRandomLanguageQuizzes("LANGUAGE", count)
				.stream()
				.map(DiagnosisQuizDto::of)
				.toList();
	}
}