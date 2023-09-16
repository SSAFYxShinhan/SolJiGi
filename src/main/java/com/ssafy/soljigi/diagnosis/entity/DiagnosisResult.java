package com.ssafy.soljigi.diagnosis.entity;

import com.ssafy.soljigi.base.entity.BaseEntity;
import com.ssafy.soljigi.user.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class DiagnosisResult extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	private int age;
	private int educationLevel;

	private int orientScore;        // 지남력
	private int attentionScore;        // 주의력
	private int spacetimeScore;        // 시공간
	private int executiveScore;        // 집행기능
	private int languageScore;        // 언어
	private int memoryScore;        // 기억력

	@Enumerated(EnumType.STRING)
	private DiagnosisResultType type;
}
