package com.ssafy.soljigi.game.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.soljigi.game.quiz.entity.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

	@Query(value = "SELECT * FROM quiz order by RAND() limit :count", nativeQuery = true)
	List<Quiz> findRandomQuizzes(@Param("count") int count);
}
