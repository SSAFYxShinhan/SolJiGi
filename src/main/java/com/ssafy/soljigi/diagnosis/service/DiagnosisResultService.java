package com.ssafy.soljigi.diagnosis.service;

import java.util.List;

import com.ssafy.soljigi.base.error.AppException;
import com.ssafy.soljigi.base.error.ErrorCode;
import org.springframework.stereotype.Service;

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

	public List<DiagnosisResultResponse> findAll(Long userId) {
		return resultRepository.findAllByUserId(userId).stream()
			.map(DiagnosisResultResponse::of)
			.toList();
	}

	//Creditial 데이터로 추출한 username
	public List<DiagnosisResultResponse> findByUserName(String username){
		User foundUser = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
		return resultRepository.findByUser(foundUser).stream()
				.map(DiagnosisResultResponse::of)
				.toList();
	}

	public DiagnosisResultResponse findById(Long id){
		DiagnosisResult diagnosisResult = resultRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.DIAGNOSIS_RESULT_NOT_FOUND));
		return DiagnosisResultResponse.of(diagnosisResult);
	}

	public Long save(DiagnosisResultSaveRequest saveRequest) {
		User user = userRepository.findById(saveRequest.getUserId())
			.orElseThrow(IllegalArgumentException::new);

		// age, educationLevel -> userEntity getter로 변경 예정
		DiagnosisResult savedId = resultRepository.save(DiagnosisResult.builder()
			.user(user)
			.age(saveRequest.getAge())
			.educationLevel(saveRequest.getEducationLevel())
			.orientScore(saveRequest.getOrientScore())
			.attentionScore(saveRequest.getAttentionScore())
			.spacetimeScore(saveRequest.getSpacetimeScore())
			.executiveScore(saveRequest.getExecutiveScore())
			.languageScore(saveRequest.getLanguageScore())
			.memoryScore(saveRequest.getMemoryScore())
			.type(getDiagnosticResult(saveRequest))
			.build());
		return savedId.getId();
	}

	private DiagnosisResultType getDiagnosticResult(DiagnosisResultSaveRequest saveRequest) {
		// 진단 검사에 따른 결과값 반환 예정
		return DiagnosisResultType.NORMAL;
	}
}
