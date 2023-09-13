// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';

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

function renderStickChart(){
    //연산해서 넣기
    let orientWidth = Math.round((orientList[orientList.length - 1] / orient) * 100);
    resultData1.style = `width: ${orientWidth}%`;
    resultData1Text.innerText = orientWidth;
    let attentionWidth = Math.round((attentionList[attentionList.length - 1] / attention) * 100);
    resultData2.style = `width: ${attentionWidth}%`;
    resultData2Text.innerText = attentionWidth;
    let spacetimeWidth = Math.round((spacetimeList[spacetimeList.length - 1] / spacetime) * 100);
    resultData3.style = `width: ${spacetimeWidth}%`;
    resultData3Text.innerText = spacetimeWidth;
    let executiveWidth = Math.round((executiveList[executiveList.length - 1] / executive) * 100);
    resultData4.style = `width: ${executiveWidth}%`;
    resultData4Text.innerText = executiveWidth;
    let languageWidth = Math.round((languageList[languageList.length - 1] / language) * 100)
    resultData5.style = `width: ${languageWidth}%`;
    resultData5Text.innerText = languageWidth;
    let memoryWidth = Math.round((memoryList[memoryList.length - 1] / memory) * 100);
    resultData6.style = `width: ${memoryWidth}%`;
    resultData6Text.innerText = memoryWidth;
}



async function getMyPayPattern(){
    let isLogin = document.getElementById("isLogin");
    if(isLogin == null){
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
    }else{
        // fetch
        let url = "/v1/search/transaction";
        let response = await fetch(url,{
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                dataHeader: {
                    apikey: "2023_Shinhan_SSAFY_Hackathon"
                },
                dataBody: {
                }
            })
        })
        if(response.ok){
            let json = await response.json();
            console.log(json);
        }


        var myPieChart = new Chart(ctx, {
            type: 'doughnut',
            data: {
                labels: ["Direct", "Referral", "Social"],
                datasets: [{
                    data: [3, 4, 5, 5, 6, 7],
                    backgroundColor: ['#4e73df', '#1cc88a', '#36b9cc'],
                    hoverBackgroundColor: ['#2e59d9', '#17a673', '#2c9faf'],
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
    }
}


async function getDataFromResult(){
    let requestId = document.getElementById("resultIndexValue").value;
    let url = `/diagnosis/data/${requestId}`;
    let response = await fetch(url);
    if(response.ok){
        let json = await response.json();
        let e= json.data;
        console.log(e);
            orientList.push(e.orientScore);
            attentionList.push(e.attentionScore);
            spacetimeList.push(e.spacetimeScore);
            executiveList.push(e.executiveScore);
            languageList.push(e.languageScore);
            memoryList.push(e.memoryScore);

            document.getElementById("resultTotalScore").innerText = e.totalScore + " / " + total;
        // 막대 바 렌더링
        renderStickChart();

    }
}

getDataFromResult();
getMyPayPattern();

// Pie Chart Example
// 데이터에 자동 비율
//


