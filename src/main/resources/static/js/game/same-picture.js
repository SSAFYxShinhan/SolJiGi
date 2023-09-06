emojis = ['😀', '😁', '😂', '😍', '😎', '🤩', '😴', '🥱', '🙄', '😜', '😳'];

class SamePictureGame {
    constructor(container, row, col, timeLimit, result) {
        this.container = container;
        this.R = row;
        this.C = col;
        this.time = timeLimit;
        this.countDownTimer = null;
        this.cardEmoji = new Array(row * col);
        this.cardSelected = new Array(row * col);
        this.gameState = state.READY;
        this.candidates = null;
        this.answerEmoji = null;
        this.fillTagElement();
        this.timerElement = document.querySelector('.same-picture__timer');
        this.cards = document.querySelectorAll('.same-picture__card');
        this.submitBtn = document.querySelector('.same-picture__submit-btn');
        this.result = result;

        this.submitBtn.addEventListener('click', () => {
            if (this.gameState !== state.GAME) return;

            this.gameState = state.READY;
            this.clearTimer();
            this.container.innerHTML = '';
            if (this.checkAnswer()) {
                ++result[gameType.SAME_PICTURE];
                alert('정답입니다!');
            } else {
                alert('오답입니다!');
            }
        });

        for (let i = 0; i < this.R * this.C; ++i) {
            this.cards[i].addEventListener('click', () => {
                if (this.gameState !== state.GAME) return;
                this.cardSelected[i] = !this.cardSelected[i];
                if (this.cardSelected[i]) {
                    this.cards[i].classList.add('same-picture__card-selected');
                } else {
                    this.cards[i].classList.remove(
                        'same-picture__card-selected'
                    );
                }
            });
        }
        this.start();
    }

    start() {
        this.clearTimer();
        const count = Math.floor(Math.random() * 5) + 4;
        this.timerElement.innerText = this.time;
        this.countDownTimer = setInterval(
            () =>
                this.countDown(
                    this.timerElement,
                    --this.time,
                    this.countDownTimer
                ),
            1000
        );
        this.gameState = state.GAME;
        this.candidates = this.generateRandomEmojis(count);
        this.cardSelected.fill(false);
        this.answerEmoji = this.candidates[0];
        this.constructMap(count);
        this.generateProblem(this.answerEmoji);
        this.cards.forEach((card) =>
            card.classList.remove('same-picture__card-selected')
        );
    }

    fillTagElement() {
        // header
        const headerElement = document.createElement('div');
        headerElement.classList.add('same-picture__header');

        const timerElement = document.createElement('div');
        timerElement.classList.add('same-picture__timer');

        const problemElement = document.createElement('div');
        problemElement.classList.add('same-picture__problem');
        problemElement.innerText = 'Problem';

        headerElement.appendChild(timerElement);
        headerElement.appendChild(problemElement);

        // content
        const contentElement = document.createElement('div');
        contentElement.classList.add('same-picture__content');
        for (let i = 0; i < this.R; ++i) {
            const divElement = document.createElement('div');
            for (let j = 0; j < this.C; ++j) {
                const cardElement = document.createElement('div');
                cardElement.classList.add('same-picture__card');
                divElement.appendChild(cardElement);
            }
            contentElement.appendChild(divElement);
        }

        // footer
        const footerElement = document.createElement('div');
        footerElement.classList.add('same-picture__footer');

        const submitButtonElement = document.createElement('button');
        submitButtonElement.classList.add('same-picture__submit-btn');
        submitButtonElement.innerText = '제출';

        footerElement.appendChild(submitButtonElement);

        this.container.appendChild(headerElement);
        this.container.appendChild(contentElement);
        this.container.appendChild(footerElement);
    }

    countDown(timerElement, time, countDownTimer) {
        timerElement.innerText = time;
        if (time === 0) {
            if (countDownTimer != null) {
                clearInterval(countDownTimer);
            }
            alert('시간 초과입니다!');
            this.container.innerHTML = '';
            this.gameState = state.READY;
        }
    }

    clearTimer() {
        if (this.countDownTimer != null) {
            clearInterval(this.countDownTimer);
        }
    }

    generateProblem(emoji) {
        const msg = document.querySelector('.same-picture__problem');
        msg.innerText = emoji + '를 모두 찾으세요!';
    }

    checkAnswer() {
        for (let i = 0; i < this.R * this.C; ++i) {
            if (this.cardEmoji[i] === this.answerEmoji) {
                if (
                    !this.cards[i].classList.contains(
                        'same-picture__card-selected'
                    )
                ) {
                    return false;
                }
            } else {
                if (
                    this.cards[i].classList.contains(
                        'same-picture__card-selected'
                    )
                ) {
                    return false;
                }
            }
        }
        return true;
    }

    constructMap(count) {
        for (let i = 0; i < this.R * this.C; ++i) {
            const num = Math.floor(Math.random() * count);
            this.cards[i].innerHTML = this.candidates[num];
            this.cardEmoji[i] = this.candidates[num];
        }
    }

    generateRandomEmojis(count) {
        const copied = [...emojis];
        copied.sort(() => Math.random() - 0.5);
        return copied.slice(0, count);
    }
}