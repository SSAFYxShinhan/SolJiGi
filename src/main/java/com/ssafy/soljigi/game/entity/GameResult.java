package com.ssafy.soljigi.game.entity;

import com.ssafy.soljigi.base.entity.BaseEntity;
import com.ssafy.soljigi.user.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class GameResult extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")	// ???
	private User user;

	private int financeCorrect;
	private int financeTotal;
	private int transactionCorrect;
	private int transactionTotal;
	private int matchCardCorrect;
	private int matchCardTotal;
	private int samePictureCorrect;
	private int samePictureTotal;
	private int correctCount;
	private int totalCount;
}
