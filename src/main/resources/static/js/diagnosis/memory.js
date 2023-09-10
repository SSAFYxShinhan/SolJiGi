class MemoryDiagnosisRead {
    constructor(container, quiz, timeLimit, nextEvent) {
        this.container = container;
        this.quiz = quiz;
        this.time = timeLimit;
        this.fillElement();
        this.timerElement = document.querySelector(".diagnosis-memory__timer");
        this.gameState = state.GAME;
        this.nextEvent = nextEvent;

        this.countDownTimer = setInterval(
            () => this.countDown(this.timerElement, --this.time, this.countDownTimer),
            1000
        );
        document.querySelector(".diagnosis-memory__btn").addEventListener("click", () => {
            this.clearTimer();
            this.container.innerHTML = "";
            this.gameState = state.READY;
            this.nextEvent();
        });
    }

    countDown(timerElement, time, countDownTimer) {
        timerElement.innerText = time;
        if (time === 0) {
            if (countDownTimer != null) {
                clearInterval(countDownTimer);
            }
            this.container.innerHTML = "";
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
    <div class="diagnosis-memory__header">
        <div class="diagnosis-memory__timer">${this.time}</div>
    </div>
    <div class="diagnosis-memory__content">
        <div class="diagnosis-memory__problem">
            <p>지금부터 외우셔야 하는 문장 하나를 보여드리겠습니다.</p>
            <p>소리내어 읽어보시고 문장을 외워주세요.</p>
            <p>제가 이 문장을 나중에 여쭤보겠습니다. 잘 기억하세요.</p>
        </div>
        <div class="diagnosis-memory__question">
            <p class="diagnosis-memory__sentence">
                <span>${this.quiz['who']}</span>(이)는 <span>${this.quiz['how']}</span>(을)를 타고<br/>
                <span>${this.quiz['where']}</span>에 가서 <span>${this.quiz['when']}</span>부터<br/>
                <span>${this.quiz['what']}</span>을(를) 했다.
            </p>
        </div>
        <button class="diagnosis-memory__btn">다 외웠습니다!</button>
        <div class="diagnosis-memory__footer"></div>
    </div>
    `;
        this.container.innerHTML = content;
    }
}

class MemoryDiagnosisQuiz {
    constructor(container, quiz, timeLimit, result, memoryResult, nextEvent) {
        this.container = container;
        this.quiz = quiz;
        this.time = timeLimit;
        this.fillElement();
        this.timerElement = document.querySelector(".diagnosis-memory__timer");
        this.result = result;
        this.gameState = state.GAME;
        this.nextEvent = nextEvent;

        this.countDownTimer = setInterval(
            () => this.countDown(this.timerElement, --this.time, this.countDownTimer),
            1000
        );
        this.form = document.querySelector('.diagnosis-memory__input-form');
        this.form.addEventListener("submit", () => {
            event.preventDefault();
            this.clearTimer();
            const userAnswer = document.querySelector('.diagnosis-memory__input').value.trim();
            console.log(userAnswer)
            for (let i = 0; i < 5; ++i) {
                console.log(this.quiz[memory4w1h[i]])
                if (userAnswer.indexOf(this.quiz[memory4w1h[i]]) > -1) {
                    memoryResult[i] += 2;
                    result[diagnosisType.MEMORY] += 2;
                }
            }
            this.container.innerHTML = "";
            this.nextEvent();
        });
    }

    countDown(timerElement, time, countDownTimer) {
        timerElement.innerText = time;
        if (time === 0) {
            if (countDownTimer != null) {
                clearInterval(countDownTimer);
            }
            this.container.innerHTML = "";
            alert("시간 초과입니다!");
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
    <div class="diagnosis-memory__header">
        <div class="diagnosis-memory__timer">${this.time}</div>
    </div>
    <div class="diagnosis-memory__content">
    <span class="diagnosis-memory__question">제가 아까 전에 외우라고<br>불러드렸던 문장을 말씀해주세요.</span>
    <form class="diagnosis-memory__input-form">`;
        content += `
        <input type="text" class="diagnosis-memory__input"/>
        <input type="submit" class="diagnosis-memory__submit-btn" value="제출">
    </div>
    <div class="diagnosis-memory-quiz__footer"></div>
    `;
        this.container.innerHTML = content;
    }
}