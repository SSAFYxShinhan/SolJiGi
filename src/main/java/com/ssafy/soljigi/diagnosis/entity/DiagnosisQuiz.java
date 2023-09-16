package com.ssafy.soljigi.diagnosis.entity;

import com.ssafy.soljigi.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class DiagnosisQuiz extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private DiagnosisType type;

	@Column(nullable = false)
	private String question;

	@OneToMany(mappedBy = "quiz")
	@BatchSize(size = 10)
	private List<DiagnosisQuizChoice> choice = new ArrayList<>();

	@ElementCollection(fetch = FetchType.LAZY)
	@BatchSize(size = 10)
	private List<String> shortAnswer;

	protected DiagnosisQuiz() {
	}

	@Builder
	public DiagnosisQuiz(DiagnosisType type, String question, List<DiagnosisQuizChoice> choice, List<String> shortAnswer) {
		this.type = type;
		this.question = question;
		if (choice != null)
			this.choice = choice;
		if (shortAnswer != null)
			this.shortAnswer = shortAnswer;
	}
}