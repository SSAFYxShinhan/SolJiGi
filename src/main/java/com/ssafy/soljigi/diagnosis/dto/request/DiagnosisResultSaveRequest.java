package com.ssafy.soljigi.diagnosis.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosisResultSaveRequest {
	private Long userId;
	private int age;
	private int educationLevel;
	private int orientScore;        // 지남력
	private int attentionScore;        // 주의력
	private int spacetimeScore;        // 시공간
	private int executiveScore;        // 집행기능
	private int languageScore;        // 언어
	private int memoryScore;        // 기억력

	public String getDiagnosticResult() {
		return "{name}님의 치매 진단 검사 결과가 다음과 같습니다.\n"
			+ "검사 진행 결과 : " + "정상";
	}
}
