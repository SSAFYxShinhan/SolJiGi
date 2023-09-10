package com.ssafy.soljigi.diagnosis.entity;

import java.util.List;

import org.hibernate.annotations.BatchSize;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

// @Entity
@Getter
@ToString
public class DiagnosisQuiz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private DiagnosisType type;

	@Column(nullable = false)
	private String question;

	@ElementCollection(fetch = FetchType.LAZY)
	@BatchSize(size = 10)
	private List<String> choice;

	private int choiceAnswer;

	@ElementCollection(fetch = FetchType.LAZY)
	@BatchSize(size = 10)
	private List<String> shortAnswer;

	protected DiagnosisQuiz() {
	}

	@Builder
	public DiagnosisQuiz(DiagnosisType type, String question, List<String> choice, int choiceAnswer, List<String> shortAnswer) {
		this.type = type;
		this.question = question;
		if (choice != null)
			this.choice = choice;
		this.choiceAnswer = choiceAnswer;
		if (shortAnswer != null)
			this.shortAnswer = shortAnswer;
	}
}