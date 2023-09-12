package com.ssafy.soljigi.game.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.soljigi.game.entity.GameResult;

public interface GameResultRepository extends JpaRepository<GameResult, Long> {

	List<GameResult> findAllByUserId(Long userId);
}
