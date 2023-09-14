/**
 * getDataFromResultPagination(); 페이징 처리, 포인트 그래프 렌더링
 * renderStickChart(); 막대바 렌더링
 *
 */

// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';


// Area Chart Example
// 라벨에 날짜에 대한 데이터를 넣고
// data 에 결과에 대한 데이터를 넣으면 됨
var ctx = document.getElementById("myPieChart");

var resultData1 = document.getElementById("resultData1");
var resultData1Text = document.getElementById("resultData1Text");
var resultData2 = document.getElementById("resultData2");
var resultData2Text = document.getElementById("resultData2Text");
var resultData3 = document.getElementById("resultData3");
var resultData3Text = document.getElementById("resultData3Text");
var resultData4 = document.getElementById("resultData4");
var resultData4Text = document.getElementById("resultData4Text");


var orientList = [];
var attentionList = [];
var spacetimeList = [];
var executiveList = [];
var totalList = [];


function renderStickChart() {
    //연산해서 넣기

    let orientWidth = Math.round((orientList[orientList.length - 2] / orientList[orientList.length - 1]) * 100);
    resultData1.style = `width: ${orientWidth}%`;
    resultData1Text.innerText = orientWidth;
    let attentionWidth = Math.round((attentionList[attentionList.length - 2] / attentionList[attentionList.length - 1]) * 100);
    resultData2.style = `width: ${attentionWidth}%`;
    resultData2Text.innerText = attentionWidth;
    let spacetimeWidth = Math.round((spacetimeList[spacetimeList.length - 2] / spacetimeList[spacetimeList.length - 1]) * 100);
    resultData3.style = `width: ${spacetimeWidth}%`;
    resultData3Text.innerText = spacetimeWidth;
    let executiveWidth = Math.round((executiveList[executiveList.length - 2] / executiveList[executiveList.length - 1]) * 100);
    resultData4.style = `width: ${executiveWidth}%`;
    resultData4Text.innerText = executiveWidth;

}


async function getDataFromResultPagination() {
    let requestId = document.getElementById("resultIndexValue").value;
    let url = `/game/data/${requestId}`;
    let response = await fetch(url);
    let paginationData;
    if (response.ok) {
        let json = await response.json();
        let e = json.data;

        orientList.push(e.financeCorrect, e.financeTotal);
        attentionList.push(e.matchCardCorrect, e.matchCardTotal);
        spacetimeList.push(e.samePictureCorrect, e.samePictureTotal);
        executiveList.push(e.transactionCorrect, e.transactionTotal);


        if (orientList.length >= 1) {
            // 막대 바 렌더링
            renderStickChart();
        }
    }
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


    }
}


getDataFromResultPagination();
getMyPayPattern();