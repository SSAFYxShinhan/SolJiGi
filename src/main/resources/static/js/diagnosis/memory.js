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
            <p>다음 문장을 읽고 기억해 주세요</p> 
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
            const userAnswer = document.querySelector('.diagnosis-memory__input').textContent;
            console.log(userAnswer)
            for (let i = 0; i < 5; ++i) {
                console.log(this.quiz[memory4w1h[i]])
                if (userAnswer.indexOf(this.quiz[memory4w1h[i]]) > -1) {
                    console.log(i + "번 키워드 정답!")
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
    <span class="diagnosis-memory__question">아까 외웠던 문장을 기억나는대로 말해주세요.</span>
    <form class="diagnosis-memory__input-form">`;
        content += `  
<!--        <input type="text" class="diagnosis-memory__input"/>-->
        <div class="short-answer-quiz__input-box">
          <p class="diagnosis-memory__input"></p> 
          <button type="button" class="short-answer-quiz__record-btn" onclick="sendSpeechMemory();">
            <i class="fas fa-solid fa-microphone"></i>
          </button> 
          <button type="button" class ="stopSpeech";">정지</button> 
        </div>
        <input type="submit" class="diagnosis-memory__submit-btn" value="제출"></div>
    </form>
    </div>
    <div class="diagnosis-memory-quiz__footer"></div>
    `;
        this.container.innerHTML = content;
    }
}

var SpeechRecognition = SpeechRecognition || webkitSpeechRecognition;
var SpeechGrammarList = SpeechGrammarList || webkitSpeechGrammarList;
var SpeechRecognitionEvent = SpeechRecognitionEvent || webkitSpeechRecognitionEvent;

function sendSpeechMemory() {
    var recognition = new SpeechRecognition();
    var speechRecognitionList = new SpeechGrammarList();
    var diagnosticPara = document.querySelector('.diagnosis-memory__input');
    recognition.grammars = speechRecognitionList;
    recognition.lang = 'ko-KR';
    recognition.interimResults = false; // true: 중간 결과를 반환, false: 최종 결과만 반환
    recognition.continious = true; // true: 음성인식을 계속해서 수행, false: 음성인식을 한번만 수행
    recognition.maxAlternatives = 1;

    recognition.start();

    // 중간에 중지 버튼을 눌렀을 때 음성 인식 중지
    document.querySelector('.stopSpeech').addEventListener('click', function() {
        recognition.stop();
    });

    // 일정 시간 후 음성 인식 중지
    setTimeout(function() {
        recognition.stop();
    }, 60000); // 60초(1분) 후에 중지하도록 설정 (원하는 시간으로 변경 가능)

    recognition.onresult = function(event) {
        var speechResult = event.results[0][0].transcript.toLowerCase();
        console.log('Speech Result: ' + speechResult);
        diagnosticPara.textContent += (" " + speechResult);
    }
}
