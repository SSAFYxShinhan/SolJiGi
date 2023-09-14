package com.ssafy.soljigi.diagnosis.dto.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import com.ssafy.soljigi.diagnosis.entity.DiagnosisResult;
import com.ssafy.soljigi.diagnosis.entity.DiagnosisResultType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DiagnosisResultResponse {

	private Long id;
	private int age;
	private int educationLevel;
	private int orientScore;        // 지남력
	private int attentionScore;        // 주의력
	private int spacetimeScore;        // 시공간
	private int executiveScore;        // 집행기능
	private int languageScore;        // 언어
	private int memoryScore;        // 기억력
	private int totalScore;
	private DiagnosisResultType result;
	private LocalDateTime registrationDate;
	private String registrationDateString;
	private boolean isDoneInMonth;

	public static DiagnosisResultResponse of(DiagnosisResult diagnosisResult) {
		return DiagnosisResultResponse.builder()
			.id(diagnosisResult.getId())
			.age(diagnosisResult.getAge())
			.educationLevel(diagnosisResult.getEducationLevel())
			.orientScore(diagnosisResult.getOrientScore())
			.attentionScore(diagnosisResult.getAttentionScore())
			.spacetimeScore(diagnosisResult.getSpacetimeScore())
			.executiveScore(diagnosisResult.getExecutiveScore())
			.languageScore(diagnosisResult.getLanguageScore())
			.memoryScore(diagnosisResult.getMemoryScore())
			.result(diagnosisResult.getType())
			.totalScore(diagnosisResult.getOrientScore()
				+ diagnosisResult.getAttentionScore()
				+ diagnosisResult.getSpacetimeScore()
				+ diagnosisResult.getExecutiveScore()
				+ diagnosisResult.getLanguageScore()
				+ diagnosisResult.getMemoryScore())
			.registrationDate(diagnosisResult.getRegistrationDate())
			.registrationDateString(diagnosisResult.getRegistrationDate()
				.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
				.isDoneInMonth(isDone(diagnosisResult.getRegistrationDate()))
			.build();
	}
	private static boolean isDone(LocalDateTime time){
		long hours = ChronoUnit.HOURS.between(time, LocalDateTime.now());
        return hours < 720;
	}
}
