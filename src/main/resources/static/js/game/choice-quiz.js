class ChoiceQuizGame {

    constructor(container, quiz, timeLimit, result, nextEvent, diagnosisType = -1, isPictureChoice = false) {
        this.container = container;
        this.quiz = quiz;
        this.time = timeLimit;
        this.isPictureChoice = isPictureChoice;
        this.fillElement();
        this.timerElement = document.querySelector(".choice-quiz__timer");
        this.gameState = state.GAME;
        this.result = result;
        this.nextEvent = nextEvent;
        this.diagnosisType = diagnosisType;

        this.countDownTimer = setInterval(
            () => this.countDown(this.timerElement, --this.time, this.countDownTimer),
            1000
        );
        this.choiceElements = document.querySelectorAll(".choice-quiz__choice");
        for (let i = 0, length = this.choiceElements.length; i < length; ++i) {
            this.choiceElements[i].addEventListener("click", () => {
                this.clearTimer();
                if (i === this.quiz.choiceAnswer) {
                    if (diagnosisType !== -1) {
                        ++result[this.diagnosisType];
                    } else {
                        const type = this.quiz.type === 'FINANCE' ? gameType.FINANCE : gameType.TRANSACTION;
                        ++result[type];
                    }
                    alert("정답입니다.");
                } else {
                    alert("오답입니다.");
                }
                this.container.innerHTML = "";
                this.gameState = state.READY;
                this.nextEvent();
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
            this.nextEvent();
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
                    <div class="choice-quiz__question"><span>${this.quiz.question}</span></div>`;

        console.log(this.isPictureChoice);
        if (this.isPictureChoice) {
            content += `<div class="choice-quiz__picture-btn-container">`;
            for (const chc of this.quiz.choice) {
                content += `<div class="choice-quiz__choice choice-quiz__picture-choice">${chc}</div>`;
            }
        } else {
            content += `<div class="choice-quiz__btn-container">`;
            for (const chc of this.quiz.choice) {
                content += `<div class="choice-quiz__choice choice-quiz__text-choice"><span>${chc}</span></div>`;
            }
        }
    content += ` 
                </div>
                </div>
                <div class="choice-quiz__footer"></div>
                `;
        this.container.innerHTML = content;
    }
}
