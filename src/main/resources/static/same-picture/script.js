const emojis = [
    '😀',
    '😁',
    '😂',
    '😍',
    '😎',
    '🤩',
    '😴',
    '🥱',
    '🙄',
    '😜',
    '😳',
];

const state = {
    GAME: 0,
    READY: 1,
};
Object.freeze(emojis);
Object.freeze(state);

const COUNT_DOWN_TIME = 10;
const cards = document.querySelectorAll('.same-picture__card');
const cardEmoji = new Array(16);
const cardSelected = new Array(16);
const startBtn = document.querySelector('.same-picture__start-btn');
const submitBtn = document.querySelector('.same-picture__submit-btn');
const timerObj = document.querySelector('.same-picture__timer');
let answerEmoji = '';
let gameState = state.READY;
let countDownTimer = null;
let timer = COUNT_DOWN_TIME;

window.onload = () => {
    for (let i = 0; i < 16; ++i) {
        cards[i].addEventListener('click', () => {
            if (gameState != state.GAME) return;
            cardSelected[i] = !cardSelected[i];
            if (cardSelected[i]) {
                cards[i].classList.add('same-picture__card-selected');
            } else {
                cards[i].classList.remove('same-picture__card-selected');
            }
        });
    }
};

startBtn.addEventListener('click', () => {
    samePictureGameStart();
});

submitBtn.addEventListener('click', () => {
    if (gameState != state.GAME) return;

    gameState = state.READY;
    clearTimer();
    if (checkAnswer()) {
        alert('정답입니다!');
    } else {
        alert('오답입니다!');
    }
});

function countDown() {
    if (timer == 0) {
        clearTimer();
        alert('시간 초과입니다!');
        return;
    }
    timerObj.innerText = --timer;
}

function clearTimer() {
    if (countDownTimer != null) {
        clearInterval(countDownTimer);
    }
}

function samePictureGameStart() {
    clearTimer();
    const count = Math.floor(Math.random() * 4) + 4;
    timer = COUNT_DOWN_TIME;
    timerObj.innerText = timer;
    countDownTimer = setInterval(countDown, 1000);
    gameState = state.GAME;
    candidates = generateRandomEmojis(count);
    cardSelected.fill(false);
    answerEmoji = candidates[0];
    constructMap(candidates, count);
    generateProblem(answerEmoji);
    cards.forEach((card) =>
        card.classList.remove('same-picture__card-selected')
    );
}

function generateProblem(emoji) {
    const msg = document.querySelector('.same-picture__problem-message');
    msg.innerText = emoji + '를 모두 찾으세요!';
}

function checkAnswer() {
    for (let i = 0; i < 16; ++i) {
        if (cardEmoji[i] === answerEmoji) {
            if (!cards[i].classList.contains('same-picture__card-selected')) {
                return false;
            }
        } else {
            if (cards[i].classList.contains('same-picture__card-selected')) {
                return false;
            }
        }
    }
    return true;
}

function constructMap(candidates, count) {
    for (let i = 0; i < 16; ++i) {
        const num = Math.floor(Math.random() * count);
        cards[i].innerHTML = candidates[num];
        cardEmoji[i] = candidates[num];
    }
}

function generateRandomEmojis(count) {
    const copied = [...emojis];
    copied.sort(() => Math.random() - 0.5);
    return copied.slice(0, count);
}
