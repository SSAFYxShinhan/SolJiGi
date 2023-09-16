package com.ssafy.soljigi.game.repository;

import java.util.List;

import com.ssafy.soljigi.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.soljigi.game.entity.GameResult;

public interface GameResultRepository extends JpaRepository<GameResult, Long> {

	List<GameResult> findAllByUserId(Long userId);
	List<GameResult> findByUser(User user);
}
