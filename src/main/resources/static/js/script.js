const state = {
    GAME: 0,
    READY: 1,
};

const gameType = {
    MATCH_CARD: 0,
    SAME_PICTURE: 1,
    FINANCE: 2,
    TRANSACTION: 3,
}

const diagnosisType = {
    ORIENT: 0,
    MEMORY: 1,
    ATTENTION: 2,
    SPACETIME: 3,
    LANGUAGE: 4,
    EXECUTIVE_VIRTUAL: 5,
    EXECUTIVE_FLUENT: 6,
}

const memory4w1h = ['who', 'how', 'where', 'when', 'what']

Object.freeze(state);
Object.freeze(gameType);
Object.freeze(diagnosisType);
Object.freeze(memory4w1h);
