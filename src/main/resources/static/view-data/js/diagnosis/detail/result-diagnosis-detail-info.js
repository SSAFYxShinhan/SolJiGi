// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';


var contentString = [
    "함께 산책하며 시간을 즐기자!",
    "자연 속에서 신선한 공기 마시며 걷는 건 어때요?",
    "우리 강아지 친구와 함께 걷기 좋은 날이에요!",
    "휴식을 즐기면서 걷는 건 어떨까요?",
    "자연의 아름다움을 느끼며 걸어보세요.",
    "마음을 정화하고 에너지를 충전하는 산책을 시작해봐요.",
    "걷면서 새로운 발견을 해보세요!",
    "산책은 몸과 마음에 좋아요.",
    "걷기를 시작하면 스트레스가 덜해질 거예요.",
    "산책 중에 좋은 음악이나 오디오북을 듣는 건 어때요?",
    "산책을 통해 몸과 마음을 활기차게 만들어봐요.",
    "우리의 모험을 시작해봅시다!",
    "이 아름다운 경로에서 함께 걸어요.",
    "산책은 일상 속에서 작은 여행이에요.",
    "같이 걷면서 얘기하며 시간을 보내는 건 어때요?",
    "산책은 건강한 라이프스타일의 일부에요.",
    "바깥 공기를 마음껏 즐기는 시간이에요.",
    "새로운 경치를 발견하면서 걷는 것은 흥미로워요.",
    "산책은 스트레스 해소에 좋아요.",
    "우리의 건강을 위해 함께 걷는 것이 중요해요."
]

const randomValue = Math.floor(Math.random() * contentString.length);
document.getElementById("recommendTextArea").innerText = contentString[randomValue];
var ctx = document.getElementById("myPieChart");

var resultData1 = document.getElementById("resultData1");
var resultData1Text = document.getElementById("resultData1Text");
var resultData2 = document.getElementById("resultData2");
var resultData2Text = document.getElementById("resultData2Text");
var resultData3 = document.getElementById("resultData3");
var resultData3Text = document.getElementById("resultData3Text");
var resultData4 = document.getElementById("resultData4");
var resultData4Text = document.getElementById("resultData4Text");
var resultData5 = document.getElementById("resultData5");
var resultData5Text = document.getElementById("resultData5Text");
var resultData6 = document.getElementById("resultData6");
var resultData6Text = document.getElementById("resultData6Text");

var orientList = [];
var attentionList = [];
var spacetimeList = [];
var executiveList = [];
var languageList = [];
var memoryList = [];
var totalList = [];


const orient = 5;
const attention = 3;
const spacetime = 2;
const executive = 4;
const language = 4;
const memory = 4;
const total = orient + attention + spacetime + executive + language + memory;

function renderStickChart() {
    //연산해서 넣기
    let orientWidth = Math.round((orientList[orientList.length - 1] / orient) * 100);
    resultData1.style = `width: ${orientWidth}%`;
    resultData1Text.innerText = orientWidth + "%";
    let attentionWidth = Math.round((attentionList[attentionList.length - 1] / attention) * 100);
    resultData2.style = `width: ${attentionWidth}%`;
    resultData2Text.innerText = attentionWidth + "%";
    let spacetimeWidth = Math.round((spacetimeList[spacetimeList.length - 1] / spacetime) * 100);
    resultData3.style = `width: ${spacetimeWidth}%`;
    resultData3Text.innerText = spacetimeWidth + "%";
    let executiveWidth = Math.round((executiveList[executiveList.length - 1] / executive) * 100);
    resultData4.style = `width: ${executiveWidth}%`;
    resultData4Text.innerText = executiveWidth + "%";
    let languageWidth = Math.round((languageList[languageList.length - 1] / language) * 100)
    resultData5.style = `width: ${languageWidth}%`;
    resultData5Text.innerText = languageWidth + "%";
    let memoryWidth = Math.round((memoryList[memoryList.length - 1] / memory) * 100);
    resultData6.style = `width: ${memoryWidth}%`;
    resultData6Text.innerText = memoryWidth + "%";


    document.getElementById("resultTotalScore").innerText = datetime +  " / 종합결과"
}


async function getMyPayPattern() {
    let isLogin = document.getElementById("isLogin");
    if (isLogin == null) {
        var chart = new Chart(ctx, {
            type: 'doughnut',
            data: {
                labels: ["로그인 필요"],
                datasets: [{
                    data: [1],
                    // hoverBorderColor: "rgba(234, 236, 244, 1)",
                }],
            },
            options: {
                maintainAspectRatio: false,
                tooltips: {
                    backgroundColor: "rgb(255,255,255)",
                    bodyFontColor: "#858796",
                    borderColor: '#dddfeb',
                    borderWidth: 1,
                    xPadding: 15,
                    yPadding: 15,
                    displayColors: false,
                    caretPadding: 10,
                },
                legend: {
                    display: false
                },
                cutoutPercentage: 80,
            },
        });
        document.getElementById("circleChartTitle").innerText = "로그인 하고 소비패턴 알기";
        document.getElementById("myPieChartLegend").innerHTML =
            '<div class="mt-4 text-center small">'
            + '<span class="mr-2">'
            + '<i class="fas fa-circle text-primary"></i> '
            + '음식'
            + '</span> '
            + '<span class="mr-2"> <i class="fas fa-circle text-success"></i> 커피 </span>'
            + '<span class="mr-2">'
            + '<i class="fas fa-circle text-info"></i> 교통 </span>'
            + '</div>'
    } else {
        // fetch
        console.log(transactionData);
        //동그란바 렌더링
        var myPieChart = new Chart(ctx, {
            type: 'doughnut',
            data: {
                labels: Object.keys(transactionData.result),
                datasets: [{
                    data: Object.values(transactionData.result),
                    backgroundColor: ['#4e73df', '#1cc88a', '#36b9cc', '#F09440', '#751A33'],
                    hoverBackgroundColor: ['#2e59d9', '#17a673', '#2c9faf', '#F0B36A', '#B34233'],
                    hoverBorderColor: "rgba(234, 236, 244, 1)",
                }],
            },
            options: {
                maintainAspectRatio: false,
                tooltips: {
                    backgroundColor: "rgb(255,255,255)",
                    bodyFontColor: "#858796",
                    borderColor: '#dddfeb',
                    borderWidth: 1,
                    xPadding: 15,
                    yPadding: 15,
                    displayColors: false,
                    caretPadding: 10,
                },
                legend: {
                    display: false
                },
                cutoutPercentage: 80,
            },
        });
        var list = Object.keys(transactionData.result);
        var size = 5;
        if(list.length > 5){
            size = 5;
        }else{
            size = list.length;
        }

        var infoDesign = ["text-primary","text-success","text-info","text-danger","text-warning"];

            var str = '<div class="mt-4 text-center small">';
            for(var step = 0; step < size ;step++){
                str += '<span class="mr-2">'
                str += `<i class="fas fa-circle ` + infoDesign[step] + '"></i>'
                str += list[step];
                str += '</span> '
            }
            str +='</div>'
            str +='</div>'


        document.getElementById("myPieChartLegend").innerHTML = str;
    }
}


async function getDataFromResult() {
    let requestId = document.getElementById("resultIndexValue").value;
    let url = `/diagnosis/data/${requestId}`;
    let response = await fetch(url);
    if (response.ok) {
        let json = await response.json();
        let e = json.data;
        console.log(e);
        orientList.push(e.orientScore);
        attentionList.push(e.attentionScore);
        spacetimeList.push(e.spacetimeScore);
        executiveList.push(e.executiveScore);
        languageList.push(e.languageScore);
        memoryList.push(e.memoryScore);

        // 막대 바 렌더링
        renderStickChart();

    }
}

getDataFromResult();
getMyPayPattern();

// Pie Chart Example
// 데이터에 자동 비율
//


