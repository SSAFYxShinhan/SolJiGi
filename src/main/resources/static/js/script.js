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

const diagnosisType = {
    ORIENT: 0,
    MEMORY: 1,
    ATTENTION: 2,
    SPACETIME: 3,
    EXECUTIVE: 4,
    LANGUAGE: 5
}

const memory4w1h = ['who', 'how', 'where', 'when', 'what']

Object.freeze(state);
Object.freeze(gameType);
Object.freeze(diagnosisType);
Object.freeze(memory4w1h);
