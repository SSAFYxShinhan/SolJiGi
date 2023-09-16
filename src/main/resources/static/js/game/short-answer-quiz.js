class ShortAnswerQuizGame {
  constructor(container, quiz, timeLimit, result, nextEvent, dType = -1) {
    this.container = container;
    this.quiz = quiz;
    this.time = timeLimit;
    this.fillElement();
    this.timerElement = document.querySelector(".short-answer-quiz__timer");
    this.result = result;
    this.nextEvent = nextEvent;
    this.dType = dType;
    console.log(dType)

    this.countDownTimer = setInterval(
      () => this.countDown(this.timerElement, --this.time, this.countDownTimer),
      1000
    );
    this.form = document.querySelector('.short-answer-quiz__input-form');
    this.form.addEventListener("submit", () => {
      event.preventDefault();
      this.clearTimer();
      // const answer = document.querySelector('.short-answer-quiz__input').value.trim().replaceAll(' ', '');
      const answer = document.querySelector('.short-answer-quiz__input').textContent;
      console.log("정답 : " + answer)

      if (this.quiz.shortAnswer.indexOf(answer) > -1) {
          if (this.dType !== -1) {
            ++result[this.dType];
          } else {
            ++result[gameType.SHORT_ANSWER_QUIZ];
          }
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
    <p class="short-answer-quiz__input"> </p> 
    <div>
    <button type="button" onclick="sendSpeechShort();">녹음</button> 
    <input type="submit" class="short-answer-quiz__submit-btn" value="제출"></div>
    </form>    
    </div>
    <div class="short-answer-quiz__footer"></div>
    `;
    this.container.innerHTML = content;
  }
}

var SpeechRecognition = SpeechRecognition || webkitSpeechRecognition;
var SpeechGrammarList = SpeechGrammarList || webkitSpeechGrammarList;
var SpeechRecognitionEvent = SpeechRecognitionEvent || webkitSpeechRecognitionEvent;

function sendSpeechShort() {
  var recognition = new SpeechRecognition();
  var speechRecognitionList = new SpeechGrammarList();
  var diagnosticPara = document.querySelector('.short-answer-quiz__input');
  recognition.grammars = speechRecognitionList;
  recognition.lang = 'ko-KR';
  recognition.interimResults = false; // true: 중간 결과를 반환, false: 최종 결과만 반환
  recognition.continious = false; // true: 음성인식을 계속해서 수행, false: 음성인식을 한번만 수행
  recognition.maxAlternatives = 1;

  recognition.start();

  recognition.onresult = function(event) {
    var speechResult = event.results[0][0].transcript.toLowerCase();
    console.log('Speech Result: ' + speechResult);
    diagnosticPara.textContent = speechResult;
  }

}