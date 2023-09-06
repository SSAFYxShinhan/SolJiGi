class ChoiceQuizGame {
    constructor(container, quiz, timeLimit, result) {
        this.container = container;
        this.quiz = quiz;
        this.time = timeLimit;
        this.fillElement();
        this.timerElement = document.querySelector(".choice-quiz__timer");
        this.gameState = state.GAME;
        this.result = result;

        this.countDownTimer = setInterval(
            () => this.countDown(this.timerElement, --this.time, this.countDownTimer),
            1000
        );
        this.choiceElements = document.querySelectorAll(".choice-quiz__choice");
        for (let i = 0, length = this.choiceElements.length; i < length; ++i) {
            this.choiceElements[i].addEventListener("click", () => {
                this.clearTimer();
                if (i === this.quiz.answer) {
                    ++result[gameType.CHOICE_QUIZ];
                    alert("정답입니다.");
                } else {
                    alert("오답입니다.");
                }
                this.container.innerHTML = "";
                this.gameState = state.READY;
            });
        }
    }

    countDown(timerElement, time, countDownTimer) {
        timerElement.innerText = time;
        if (time === 0) {
            if (countDownTimer != null) {
                clearInterval(countDownTimer);
            }
            this.container.innerHTML = "";
            alert("시간 초과입니다!");
            this.gameState = state.READY;
        }
    }

    clearTimer() {
        if (this.countDownTimer != null) {
            clearInterval(this.countDownTimer);
        }
    }

    fillElement() {
        let content = "";
        content += `
    <div class="choice-quiz__header">
        <div class="choice-quiz__timer">${this.time}</div>
    </div>
    <div class="choice-quiz__content">
    <span class="choice-quiz__question">${this.quiz.question}</span>
    <div class="choice-quiz_btn-container">`;
        for (const chc of this.quiz.choice) {
            content += `<button class="choice-quiz__choice">${chc}</button>`;
        }
        content += `
        </div>
    </div>
    <div class="choice-quiz__footer"></div>
    `;
        this.container.innerHTML = content;
    }
}
