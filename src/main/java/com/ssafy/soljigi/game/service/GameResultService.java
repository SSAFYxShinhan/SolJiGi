package com.ssafy.soljigi.game.service;

import org.springframework.stereotype.Service;

import com.ssafy.soljigi.game.dto.GameResultSaveRequest;
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

	public Long save(GameResultSaveRequest saveRequest) {
		User user = userRepository.findById(saveRequest.getUserId())
			.orElseThrow(IllegalArgumentException::new);

		// age, educationLevel -> userEntity getter로 변경 예정
		GameResult saved = resultRepository.save(GameResult.builder()
			.user(user)
			.choiceCorrect(saveRequest.getChoiceCorrect())
			.choiceTotal(saveRequest.getChoiceTotal())
			.shortAnsCorrect(saveRequest.getShortAnsCorrect())
			.shortAnsTotal(saveRequest.getShortAnsTotal())
			.matchCardCorrect(saveRequest.getMatchCardCorrect())
			.matchCardTotal(saveRequest.getMatchCardTotal())
			.samePictureCorrect(saveRequest.getSamePictureCorrect())
			.samePictureTotal(saveRequest.getSamePictureTotal())
			.correctCount(saveRequest.getCorrectCount())
			.totalCount(saveRequest.getTotalCount())
			.build());
		return saved.getId();
	}
}
