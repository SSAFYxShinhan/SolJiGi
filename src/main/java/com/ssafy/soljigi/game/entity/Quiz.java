package com.ssafy.soljigi.game.entity;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.soljigi.base.entity.BaseEntity;
import com.ssafy.soljigi.diagnosis.entity.DiagnosisType;
import jakarta.persistence.*;
import org.hibernate.annotations.BatchSize;

import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Quiz extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private Type type;

	@Column(nullable = false)
	private String question;

	@OneToMany(mappedBy = "quiz")
	@BatchSize(size = 10)
	private List<QuizChoice> choice = new ArrayList<>();

	@ElementCollection(fetch = FetchType.LAZY)
	@BatchSize(size = 10)
	private List<String> shortAnswer;

	protected Quiz() {
	}

	@Builder
	public Quiz(Type type, String question, List<QuizChoice> choice, int choiceAnswer, List<String> shortAnswer) {
		this.type = type;
		this.question = question;
		if (choice != null)
			this.choice = choice;
		if (shortAnswer != null)
			this.shortAnswer = shortAnswer;
	}
}