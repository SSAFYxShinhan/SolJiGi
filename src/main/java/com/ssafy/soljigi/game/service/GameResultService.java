package com.ssafy.soljigi.game.service;

import java.util.List;

import com.ssafy.soljigi.base.error.AppException;
import com.ssafy.soljigi.base.error.ErrorCode;
import com.ssafy.soljigi.diagnosis.dto.response.DiagnosisResultResponse;
import com.ssafy.soljigi.diagnosis.entity.DiagnosisResult;
import org.springframework.stereotype.Service;

import com.ssafy.soljigi.game.dto.request.GameResultSaveRequest;
import com.ssafy.soljigi.game.dto.response.GameResultResponse;
import com.ssafy.soljigi.game.entity.GameResult;
import com.ssafy.soljigi.game.repository.GameResultRepository;
import com.ssafy.soljigi.user.entity.User;
import com.ssafy.soljigi.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GameResultService {

	private final UserRepository userRepository;
	private final GameResultRepository resultRepository;

	public List<GameResultResponse> findAll(Long userId) {
		return resultRepository.findAllByUserId(userId).stream()
			.map(GameResultResponse::of)
			.toList();
	}

	public Long save(Long userId, GameResultSaveRequest saveRequest) {
		User user = userRepository.findById(userId)
			.orElseThrow(IllegalArgumentException::new);

		user.increasePoint(saveRequest.getCorrectCount());    // 맞춘 문제 수 만큼 포인트 증가

		GameResult saved = resultRepository.save(GameResult.builder()
			.user(user)
			.financeCorrect(saveRequest.getFinanceCorrect())
			.financeTotal(saveRequest.getFinanceTotal())
			.transactionCorrect(saveRequest.getTransactionCorrect())
			.transactionTotal(saveRequest.getTransactionTotal())
			.matchCardCorrect(saveRequest.getMatchCardCorrect())
			.matchCardTotal(saveRequest.getMatchCardTotal())
			.samePictureCorrect(saveRequest.getSamePictureCorrect())
			.samePictureTotal(saveRequest.getSamePictureTotal())
			.correctCount(saveRequest.getCorrectCount())
			.totalCount(saveRequest.getTotalCount())
			.build());
		return saved.getId();
	}

	public List<GameResultResponse> findByUserName(String username) {
		User foundUser = userRepository.findByUsername(username)
				.orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
		return resultRepository.findByUser(foundUser)
				.stream()
				.map(GameResultResponse::of)
				.sorted(((o1, o2) -> o2.getRegistrationDate().compareTo(o1.getRegistrationDate())))
				.toList();
	}

	public GameResultResponse findById(Long id) {
		GameResult gameResult = resultRepository.findById(id)
				.orElseThrow(() -> new AppException(ErrorCode.GAME_RESULT_NOT_FOUND));
		return GameResultResponse.of(gameResult);
	}


}
