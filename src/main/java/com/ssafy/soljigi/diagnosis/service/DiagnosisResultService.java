package com.ssafy.soljigi.diagnosis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.soljigi.base.error.AppException;
import com.ssafy.soljigi.base.error.ErrorCode;
import com.ssafy.soljigi.diagnosis.dto.request.DiagnosisResultSaveRequest;
import com.ssafy.soljigi.diagnosis.dto.response.DiagnosisResultResponse;
import com.ssafy.soljigi.diagnosis.entity.DiagnosisResult;
import com.ssafy.soljigi.diagnosis.entity.DiagnosisResultType;
import com.ssafy.soljigi.diagnosis.repository.DiagnosisResultRepository;
import com.ssafy.soljigi.user.entity.User;
import com.ssafy.soljigi.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiagnosisResultService {

	private final UserRepository userRepository;
	private final DiagnosisResultRepository resultRepository;

	private static final int[][] score = {
			{ 0,  0,  0,  0,  0,  0,  0},
			{ 0,  0,  0, 22, 24, 26, 27},
			{ 0,  0, 16, 21, 23, 25, 26},
			{ 0, 13, 14, 19, 22, 22, 25},
			{ 0, 10, 11, 16, 18, 20, 22},
	};

	public List<DiagnosisResultResponse> findAll(Long userId) {
		return resultRepository.findAllByUserId(userId).stream()
			.map(DiagnosisResultResponse::of)
			.toList();
	}

	//Creditial 데이터로 추출한 username
	public List<DiagnosisResultResponse> findByUserName(String username) {
		User foundUser = userRepository.findByUsername(username)
			.orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
		return resultRepository.findByUser(foundUser)
			.stream()
			.map(DiagnosisResultResponse::of)
			.sorted(((o1, o2) -> o2.getRegistrationDate().compareTo(o1.getRegistrationDate())))
			.toList();
	}

	public DiagnosisResultResponse findById(Long id) {
		DiagnosisResult diagnosisResult = resultRepository.findById(id)
			.orElseThrow(() -> new AppException(ErrorCode.DIAGNOSIS_RESULT_NOT_FOUND));
		return DiagnosisResultResponse.of(diagnosisResult);
	}

	public Long save(Long userId, DiagnosisResultSaveRequest saveRequest) {
		User user = userRepository.findById(userId)
			.orElseThrow(IllegalArgumentException::new);

		int age = user.getAge();
		int educationLevel = user.getEducationLevel();

		DiagnosisResult savedId = resultRepository.save(DiagnosisResult.builder()
			.user(user)
			.age(age)
			.educationLevel(educationLevel)
			.orientScore(saveRequest.getOrientScore())
			.attentionScore(saveRequest.getAttentionScore())
			.spacetimeScore(saveRequest.getSpacetimeScore())
			.executiveScore(saveRequest.getExecutiveScore())
			.languageScore(saveRequest.getLanguageScore())
			.memoryScore(saveRequest.getMemoryScore())
			.type(getDiagnosticResult(age, educationLevel, saveRequest))
			.build());
		return savedId.getId();
	}

	private DiagnosisResultType getDiagnosticResult(int age, int educationLevel, DiagnosisResultSaveRequest saveRequest) {
		int ageRange = getAgeRange(age);
		int total = saveRequest.getOrientScore() + saveRequest.getAttentionScore() + saveRequest.getSpacetimeScore()
					+ saveRequest.getMemoryScore() + saveRequest.getLanguageScore() + saveRequest.getExecutiveScore();
		return score[ageRange][educationLevel] > total ? DiagnosisResultType.DOUBT : DiagnosisResultType.NORMAL;
	}

	private static int getAgeRange(int age) {
		if (50 <= age && age < 60)
			return 1;
		else if (60 <= age && age < 70)
			return 2;
		else if (70 <= age && age < 80)
			return 3;
		else if (80 <= age)
			return 4;
		else
			return 0;
	}
}
