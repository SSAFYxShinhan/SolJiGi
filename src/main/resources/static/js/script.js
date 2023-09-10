const state = {
    GAME: 0,
    READY: 1,
};

const gameType = {
    MATCH_CARD: 0,
    SAME_PICTURE: 1,
    CHOICE_QUIZ: 2,
    SHORT_ANSWER_QUIZ: 3,
}

Object.freeze(state);
Object.freeze(gameType);