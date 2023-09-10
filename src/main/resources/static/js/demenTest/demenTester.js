// 오늘의 날짜
let today = new Date();
let year = today.getFullYear();

// 문제 객체(생성자 함수)
function Question(text, choice, answer) {
    this.text = text; // 질문 텍스트
    this.choice = choice; // 선택할 답들(배열)
    this.answer = answer; // 정답 정보
}

// 퀴즈 정보 객체
function Quiz(questions) {
    this.score = 0; // 점수
    this.questions = questions; // 문제
    this.questionIndex = 0; // 문제 번호
}

// 정답 확인 메서드
Quiz.prototype.correctAnswer = function (answer) {
    return answer == this.questions[this.questionIndex].answer;
}

var questions = [
    new Question('오늘은 몇 년도 인가요?', [year - 2, year - 1, year, year + 1], year),
];

// 퀴즈 객체 생성
var quiz = new Quiz(questions);

// 문제 출력 함수
function updateQuiz() {
    var question = document.getElementById('question');
    var idx = quiz.questionIndex + 1;
    var choice = document.querySelectorAll('.btn');

    // 문제 출력
    question.innerHTML = '문제' + idx + ') ' + quiz.questions[quiz.questionIndex].text;

    // 선택 출력
    for (var i = 0; i < 4; i++) {
        choice[i].innerHTML = quiz.questions[quiz.questionIndex].choice[i];
    }
    progress();
}

function progress() {
    var progress = document.getElementById('progress');
    progress.innerHTML = '문제 ' + (quiz.questionIndex + 1) + '/ ' + quiz.questions.length;
}

var btn = document.querySelectorAll('.btn');

// 입력 및 정답 확인 함수
function checkAnswer(i) {
    btn[i].addEventListener('click', function () {
        var answer = btn[i].innerText;

        if (quiz.correctAnswer(answer)) {
            // alert('정답입니다!');
            quiz.score++;
        } else {
            // alert('틀렸습니다!');
        }

        if (quiz.questionIndex < quiz.questions.length - 1) {
            quiz.questionIndex++;
            updateQuiz();
        } else {
            result();
        }
    });
}

function result() {
    var quizDiv = document.getElementById('quiz');
    var per = parseInt((quiz.score * 100) / quiz.questions.length);
    var txt = '<h1>결과</h1>' + '<h2 id="score">당신의 점수: ' + quiz.score + '/' + quiz.questions.length + '<br><br>' + per + '점' + '</h2>';

    let score = {
        method : "POST",
        headers : {
          "Content-Type" : "application/json",
        },
        body: JSON.stringify({
            orient: 16,
            memory: 25,
            attention : 15,
            space : 19,
            exec : 20,
            lang : 25,
            total : 100,
        })
    }
    fetch("test/result",score)
        .then((response) => {
            if(!response.ok) {
                throw new Error("400또는 500 에러 발생!");
            }
            return response.json();
        })
        .then((rst) => {
            console.log(rst);
        })
        .catch(() => {
            console.log("error!");
        });


    quizDiv.innerHTML = txt;

    // 점수별 결과 텍스트
    if (per < 60) {
        txt += '<h2>기준 미달입니다. 정밀 검사를 권장합니다.</h2>';
        quizDiv.innerHTML = txt;
    } else if (per >= 60 && per < 80) {
        txt += '<h2>정상범주이나, 활발한 뇌 운동을 권장합니다.</h2>'
        quizDiv.innerHTML = txt;
    } else if (per >= 80) {
        txt += '<h2>훌륭합니다</h2>'
        quizDiv.innerHTML = txt;
    }
}

for (var i = 0; i < btn.length; i++) {
    checkAnswer(i);
}

updateQuiz();
