package com.ssafy.soljigi.diagnosis.repository;

import com.ssafy.soljigi.diagnosis.entity.DiagnosisQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiagnosisQuizRepository extends JpaRepository<DiagnosisQuiz, Long> {

    @Query(value = "SELECT * FROM diagnosis_quiz where type = :dType order by RAND() limit :count", nativeQuery = true)
    List<DiagnosisQuiz> findRandomLanguageQuizzes(@Param("dType") String type, @Param("count") int count);
}