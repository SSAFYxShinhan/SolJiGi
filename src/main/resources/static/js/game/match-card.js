const imgSourcePrefix = '/img/friends/';
const imgSource = [
    'friends1.png',
    'friends2.png',
    'friends3.png',
    'friends4.png',
    'friends5.png',
    'friends6.png',
    'friends7.png',
    'friends8.png',
    'friends9.png',
    'friends10.png',
    'friends11.png',
];

Object.freeze(imgSource);

class MatchCardGame {
    constructor(container, row, col, timeLimit, result, nextEvent, isDiagnosis = null) {
        this.CARD_SHOW_TIME = 5;
        this.container = container;
        this.R = row;
        this.C = col;
        this.timeLimit = timeLimit;
        this.time = timeLimit;
        this.showTime = this.CARD_SHOW_TIME;
        this.gameMap = null;
        this.imgSrcMap = null;
        this.cardOpened = null;
        this.selectedIdx = 0;
        this.matchCardCount = 0;
        this.allMatchCount = 0;
        this.countDownTimer = null;
        this.gameState = null;
        this.R = row;
        this.C = col;
        this.result = result;
        this.nextEvent = nextEvent;
        this.isDiagnosis = isDiagnosis;
        this.gameInitialize();

        this.timerElement = document.querySelector('.match-card__timer');
        this.selectedCursor = [
            [-1, -1],
            [-1, -1],
        ];
        this.start();
    }

    countDown(timerElement, time, countDownTimer, game) {
        timerElement.innerText = time;
        if (time === 0) {
            if (countDownTimer != null) {
                clearInterval(countDownTimer);
            }
            if (game === false)
                return;
            this.container.innerHTML = '';
            alert('시간 초과입니다!');
            this.gameState = state.READY;
            this.nextEvent();
        }
    }

    clearTimer() {
        if (this.countDownTimer != null) {
            clearInterval(this.countDownTimer);
        }
    }

    start() {
        this.gameState = state.GAME;
        this.cardAllOpen();

        const timer = setInterval(
            () =>
                this.countDown(
                    this.timerElement,
                    --this.showTime,
                    timer,
                    false
                ),
            1000
        );

        setTimeout(() => {
            this.cardAllHide();
            this.timer = this.timeLimit;
            this.timerElement.innerText = this.timer;
            this.countDownTimer = setInterval(
                () =>
                    this.countDown(
                        this.timerElement,
                        --this.time,
                        this.countDownTimer
                    ),
                1000
            );
            console.log(this.countDownTimer);
        }, this.CARD_SHOW_TIME * 1000);
    }

    gameInitialize() {
        this.gameMap = Array.from(Array(this.R), () => new Array(this.C));
        this.imgSrcMap = Array.from(Array(this.R), () => new Array(this.C));
        this.cardOpened = Array.from(Array(this.R), () => new Array(this.C));
        this.selectedIdx = 0;
        this.matchCardCount = 0;
        this.allMatchCount = (this.R * this.C) >> 1;
        this.constructMap();
    }

    cardAllOpen() {
        for (let i = 0; i < this.R; ++i) {
            for (let j = 0; j < this.C; ++j) {
                this.cardOpen(i, j);
            }
        }
    }

    cardAllHide() {
        for (let i = 0; i < this.R; ++i) {
            for (let j = 0; j < this.C; ++j) {
                this.cardHide(i, j);
            }
        }
    }

    cardOpen(x, y) {
        this.cardOpened[x][y] = true;
        this.gameMap[x][y].classList.add('match-card__card-open');
    }

    cardHide(x, y) {
        this.cardOpened[x][y] = false;
        this.gameMap[x][y].classList.remove('match-card__card-open');
    }

    checkSelectedPair() {
        let [fi, fj] = this.selectedCursor[0];
        let [si, sj] = this.selectedCursor[1];
        if (this.imgSrcMap[fi][fj] === this.imgSrcMap[si][sj]) {
            console.log('match');
            this.selectedIdx = 0;
            ++this.matchCardCount;
            if (this.matchCardCount === this.allMatchCount) {
                setTimeout(() => {
                    this.clearTimer();
                    alert('성공');
                    if (this.isDiagnosis) {
                        ++result[diagnosisType.SPACETIME];
                    } else {
                        ++result[gameType.MATCH_CARD];
                    }
                    this.container.innerHTML = '';
                    this.gameState = state.READY;
                    this.nextEvent();
                }, 500);
            }
        } else {
            console.log('wrong!!!');
            setTimeout(() => {
                this.cardHide(fi, fj);
                this.cardHide(si, sj);
                this.selectedIdx = 0;
            }, 1000);
        }
    }

    generateRandomCardPairs(count) {
        let copied = [...imgSource];
        copied.sort(() => Math.random() - 0.5);
        copied = copied.slice(0, count).concat(copied.slice(0, count));
        copied.sort(() => Math.random() - 0.5);
        return copied;
    }

    constructMap() {
        const cardImgs = this.generateRandomCardPairs(this.allMatchCount);

        const headerElement = document.createElement('div');
        headerElement.classList.add('match-card__header');
        const problemElement = document.createElement('div');
        problemElement.classList.add('match-card__problem');
        problemElement.innerText = '그림의 짝을 외우세요';
        const timerElement = document.createElement('div');
        timerElement.classList.add('match-card__timer');
        timerElement.innerText = this.CARD_SHOW_TIME;
        headerElement.appendChild(problemElement);
        headerElement.appendChild(timerElement);

        const contentElement = document.createElement('div');
        contentElement.classList.add('match-card__content');

        for (let i = 0, idx = 0; i < this.R; ++i) {
            const rowElement = document.createElement('div');
            rowElement.classList.add('match-card__content-row');
            for (let j = 0; j < this.C; ++j) {
                const flipCardElement = this.generateCardElement(cardImgs[idx]);
                flipCardElement.addEventListener('click', () => {
                    if (this.gameState !== state.GAME) return;
                    if (!this.cardOpened[i][j] && this.selectedIdx < 2) {
                        this.cardOpen(i, j);
                        this.selectedCursor[this.selectedIdx++] = [i, j];
                        if (this.selectedIdx === 2) {
                            this.checkSelectedPair();
                        }
                    }
                });
                rowElement.appendChild(flipCardElement);
                this.gameMap[i][j] = flipCardElement;
                this.imgSrcMap[i][j] = cardImgs[idx];
                ++idx;
            }
            contentElement.appendChild(rowElement);
        }
        const footerElement = document.createElement('div');
        footerElement.classList.add('match-card__footer');
        this.container.appendChild(headerElement);
        this.container.appendChild(contentElement);
        this.container.appendChild(footerElement);
    }

    generateCardElement(fileName) {
        const flipElement = document.createElement('div');
        const cardElement = document.createElement('div');
        const imgElement = document.createElement('img');
        imgElement.setAttribute('src', imgSourcePrefix + fileName);
        imgElement.classList.add('match-card__card-front');
        const backImgElement = document.createElement('img');
        backImgElement.setAttribute('src', imgSourcePrefix + 'back.png');
        backImgElement.classList.add('match-card__card-back');
        cardElement.appendChild(imgElement);
        cardElement.appendChild(backImgElement);
        cardElement.classList.add('match-card__card');
        flipElement.classList.add('match-card__flip');
        flipElement.appendChild(cardElement);
        return flipElement;
    }
}