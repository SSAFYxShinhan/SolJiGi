const imgSourcePrefix = './img/friends/';
const imgSource = [
    'front1.png',
    'front2.png',
    'front3.png',
    'front4.png',
    'front5.png',
    'front6.png',
    'front7.png',
    'front8.png',
    // 'SOL.png',
    // 'LAY.png',
    // 'LULULALA.png',
    // 'MOLI.png',
    // 'PLI.png',
    // 'DO.png',
    // 'RE.png',
    // 'MI.png',
    // 'RINO.png',
    // 'SHOO.png',
    // 'PLILAY.png',
    // 'Friends.png',
];

Object.freeze(imgSource);
Object.freeze(state);

class MatchCardGame {
    constructor(row, col, timeLimit) {
        this.CARD_SHOW_TIME = 3;
        this.container = document.querySelector('.gameContainer');
        this.R = row;
        this.C = col;
        this.timeLimit = timeLimit;
        this.timeLimit = timeLimit;
        this.time = timeLimit;
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
        this.gameInitialize();

        this.gameContent = document.querySelector('.match-card__content');
        this.timerElement = document.querySelector('.match-card__timer');
        this.selectedCursor = [
            [-1, -1],
            [-1, -1],
        ];
    }

    countDown(timerElement, time, countDownTimer) {
        timerElement.innerText = time;
        if (time == 0) {
            if (countDownTimer != null) {
                clearInterval(countDownTimer);
            }
            this.container.innerHTML = '';
            alert('시간 초과입니다!');
            return;
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
                    this.container.innerHTML = '';
                    this.gameState = state.READY;
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
        const timerElement = document.createElement('div');
        timerElement.classList.add('match-card__timer');
        timerElement.innerText = '그림의 짝을 외우세요';
        headerElement.appendChild(timerElement);

        const contentElement = document.createElement('div');
        contentElement.classList.add('match-card__content');

        console.log(1, this.R);
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

new MatchCardGame(4, 4, 30).start();