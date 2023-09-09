class ShortAnswerQuizGame {
  constructor(container, quiz, timeLimit, result, nextEvent) {
    this.container = container;
    this.quiz = quiz;
    this.time = timeLimit;
    this.fillElement();
    this.timerElement = document.querySelector(".short-answer-quiz__timer");
    this.result = result;
    this.nextEvent = nextEvent;

    this.countDownTimer = setInterval(
      () => this.countDown(this.timerElement, --this.time, this.countDownTimer),
      1000
    );
    this.form = document.querySelector('.short-answer-quiz__input-form');
    this.form.addEventListener("submit", () => {
      event.preventDefault();
      this.clearTimer();
      const answer = document.querySelector('.short-answer-quiz__input').value.trim();

      if (this.quiz.shortAnswer.indexOf(answer) > -1) {
          ++result[gameType.SHORT_ANSWER_QUIZ];
          alert("정답입니다.");
      } else {
          alert("오답입니다.");
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
    <div class="short-answer-quiz__header">
        <div class="short-answer-quiz__timer">${this.time}</div>
    </div>
    <div class="short-answer-quiz__content">
    <span class="short-answer-quiz__question">${this.quiz.question}</span>
    <form class="short-answer-quiz__input-form">`;
    content += `
        <input type="text" class="short-answer-quiz__input"/>
        <input type="submit" class="short-answer-quiz__submit-btn" value="제출">
    </div>
    <div class="short-answer-quiz__footer"></div>
    `;
    this.container.innerHTML = content;
  }
}