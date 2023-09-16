class FluentQuizGame {
    constructor(container, quiz, timeLimit, result, nextEvent, dType = -1) {
        this.container = container;
        this.quiz = quiz;
        this.time = timeLimit;
        this.fillElement();
        this.timerElement = document.querySelector(".short-answer-quiz__timer");
        this.result = result;
        this.nextEvent = nextEvent;
        this.dType = dType;

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

            const fruits = new Set(answer.trim().split(" "));
            console.log(fruits);
            let countAnswer =0;
            console.log("answer = " + answer);
            fruits.forEach(fruit => {
                if (fruit === "") {
                    fruit = " ";
                }

                if (FruitSet.has(fruit)) {
                    console.log("정답 count : " + fruit);
                    countAnswer++;
                }

            });

            if (this.dType !== -1) {
                if(countAnswer >= 10) {
                    result[this.dType] += 2;
                } else if (countAnswer >= 7) {
                    result[this.dType] += 1;
                }
            }

            this.container.innerHTML = "";
            // this.nextEvent();
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
    <div class="short-answer-quiz__input-box">
          <p class="short-answer-quiz__input"></p> 
          <button type="button" class="short-answer-quiz__record-btn" onclick="sendSpeechFluent();">
            <i class="fas fa-solid fa-microphone"></i>
          </button> 
    </div>     
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

function sendSpeechFluent() {
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
        diagnosticPara.textContent += (" " + speechResult);
    }

}

const FruitSet = new Set([
    "석류",
    "감",
    "포도",
    "배",
    "레몬",
    "사과",
    "파인애플",
    "복숭아",
    "코코넛",
    "칼라만시",
    "유자",
    "자몽",
    "수박",
    "멜론",
    "블랙베리",
    "블루베리",
    "무화과",
    "아로니아",
    "구아바",
    "레드향",
    "한라봉",
    "귤",
    "천혜향",
    "토마토",
    "딸기",
    "체리",
    "복분자",
    "타마릴로",
    "아보카도",
    "두리안",
    "올리브",
    "백도",
    "키위",
    "바나나",
    "살구",
    "매실",
    "라임",
    "망고",
    "샤인머스켓",
    "오디",
    "참외",
    "오렌지",
    "앵두",
    "신고",
    "람부탄",
    "크랜베리",
    "으름",
    "다래",
    "머루",
    "거봉",
    "용과",
    "만다린",
    "포멜로",
    "시라",
    "청견",
    "캔털루프",
    "플럼코트",
    "판타오",
    "쿠푸아수",
    "두쿠",
    "양광",
    "구기자",
    "대추야자",
    "두꾸",
    "마르멜로",
    "스위티",
    "페피노",
    "리슬링",
    "샤르도네",
    "살라크",
    "모과",
    "파파야",
    "홍옥",
    "델라웨어",
    "가보스",
    "당근",
    "브로콜리",
    "양파",
    "고구마",
    "오이",
    "시금치",
    "버섯",
    "감자",
    "상추",
    "파프리카",
    "콩",
    "호박",
    "무",
    "고추",
    "가지",
    "깻잎",
    "마늘",
    "아스파라거스",
    "셀러리",
    "생강",
    "파슬리",
    "콜라비",
    "봄동",
    "아욱",
    "시래기",
    "브로콜리",
    "크레송",
    "부추",
    "칠레",
    "파",
    "허브",
    "라따뚜이",
    "옥수수",
    "피망",
    "배추",
    "생강",
]);
Object.freeze(FruitSet);