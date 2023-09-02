package com.ssafy.soljigi.game.quiz.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String question;

	@ElementCollection
	private List<String> options;

	@Column(nullable = false)
	private int answer;

	protected Quiz() {
	}

	@Builder
	public Quiz(Long id, String question, List<String> options, int answer) {
		this.id = id;
		this.question = question;
		this.options = options;
		this.answer = answer;
	}
}