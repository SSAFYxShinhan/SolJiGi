package com.ssafy.soljigi.diagnosis.entity;

import com.ssafy.soljigi.game.entity.Quiz;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class DiagnosisQuizChoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private DiagnosisQuiz quiz;

    private String choiceString;
    private boolean isAnswer;

}
