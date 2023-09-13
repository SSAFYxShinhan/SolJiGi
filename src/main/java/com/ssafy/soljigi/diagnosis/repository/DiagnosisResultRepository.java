package com.ssafy.soljigi.diagnosis.repository;

import java.util.List;

import com.ssafy.soljigi.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.soljigi.diagnosis.entity.DiagnosisResult;

public interface DiagnosisResultRepository extends JpaRepository<DiagnosisResult, Long> {

	@Query("select dr from DiagnosisResult dr where dr.user.id = :userId")
	List<DiagnosisResult> findAllByUserId(@Param("userId") Long userId);

	List<DiagnosisResult> findByUser(User user);
}
