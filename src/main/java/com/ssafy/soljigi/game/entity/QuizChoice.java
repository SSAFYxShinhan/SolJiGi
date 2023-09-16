package com.ssafy.soljigi.game.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class QuizChoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    private String choiceString;
    private boolean isAnswer;

}
