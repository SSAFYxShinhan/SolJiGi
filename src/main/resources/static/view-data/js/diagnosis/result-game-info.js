/**
 * getDataFromResultPagination(); 페이징 처리, 포인트 그래프 렌더링
 * renderStickChart(); 막대바 렌더링
 *
 */

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
document.getElementById("todayMention").innerText = contentString[Math.floor(Math.random()*(contentString.length))];

// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';

function number_format(number, decimals, dec_point, thousands_sep) {
    // *     example: number_format(1234.56, 2, ',', ' ');
    // *     return: '1 234,56'
    number = (number + '').replace(',', '').replace(' ', '');
    var n = !isFinite(+number) ? 0 : +number,
        prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
        sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
        dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
        s = '',
        toFixedFix = function (n, prec) {
            var k = Math.pow(10, prec);
            return '' + Math.round(n * k) / k;
        };
    // Fix for IE parseFloat(0.55).toFixed(0) = 0;
    s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
    if (s[0].length > 3) {
        s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
    }
    if ((s[1] || '').length < prec) {
        s[1] = s[1] || '';
        s[1] += new Array(prec - s[1].length + 1).join('0');
    }
    return s.join(dec);
}


// Area Chart Example
// 라벨에 날짜에 대한 데이터를 넣고
// data 에 결과에 대한 데이터를 넣으면 됨
var ctx = document.getElementById("myAreaChart");

var resultData1 = document.getElementById("resultData1");
var resultData1Text = document.getElementById("resultData1Text");
var resultData2 = document.getElementById("resultData2");
var resultData2Text = document.getElementById(  "resultData2Text");
var resultData3 = document.getElementById("resultData3");
var resultData3Text = document.getElementById("resultData3Text");
var resultData4 = document.getElementById("resultData4");
var resultData4Text = document.getElementById("resultData4Text");


var orientList = [];
var attentionList = [];
var spacetimeList = [];
var executiveList = [];
var totalList = [];

function ascCompareEvents(a, b){
    var timeA = new Date(a.registrationDate)
    var timeB = new Date(b.registrationDate)
    return timeA - timeB
}
function descCompareEvents(a, b){
    var timeA = new Date(a.registrationDate)
    var timeB = new Date(b.registrationDate)
    return timeB - timeA
}
function renderStickChart() {
    //연산해서 넣기

    let orientWidth = Math.round((orientList[orientList.length - 2] / orientList[orientList.length - 1]) * 100);
    resultData1.style = `width: 0%`;
    resultData1Text.innerText = 0;
    let attentionWidth = Math.round((attentionList[attentionList.length - 2] / attentionList[attentionList.length - 1]) * 100);
    resultData2.style = `width: 0%`;
    resultData2Text.innerText = 0;
    let spacetimeWidth = Math.round((spacetimeList[spacetimeList.length - 2] / spacetimeList[spacetimeList.length - 1]) * 100);
    resultData3.style = `width: 0%`;
    resultData3Text.innerText = 0;
    let executiveWidth = Math.round((executiveList[executiveList.length - 2] / executiveList[executiveList.length - 1]) * 100);
    resultData4.style = `width: 0%`;
    resultData4Text.innerText = 0;

}


async function getDataFromResultPagination() {
    let url = "/game/data"
    let response = await fetch(url);
    let paginationData;
    let jsondata;
    if (response.ok) {
        // let json = await response.json();
        // let paginationData = json;
        // let jsondata = json.data;
        // let ascGraphData = jsondata.sort(ascCompareEvents);
        // totalList =ascGraphData.map(e => e.totalScore);
        // let timeData = json.data.map(e => e.registrationDateString);
        let json = await response.json();
        let paginationData = json;
        jsondata = json.data;
        let ascdata = jsondata.sort(ascCompareEvents);
        totalList = ascdata.map(e => e.correctCount);

        let timeData = ascdata.map(e => e.registrationDateString);


        if (json.data[0] != null) {
            let currentDateTime = json.data[0].doneInMonth;
            if (currentDateTime) {
                document.getElementById("monthIsDone").innerText = "완료"
            } else {
                document.getElementById("monthIsDone").innerText = "미완료"
            }
        } else {

            document.getElementById("monthIsDone").innerText = "미완료"
        }
        ascdata.map(e => {
            orientList.push(e.financeCorrect, e.financeTotal);
            attentionList.push(e.transactionCorrect, e.transactionTotal);
            spacetimeList.push(e.matchCardCorrect, e.matchCardTotal);
            executiveList.push(e.samePictureCorrect, e.samePictureTotal);
        });

        if (orientList.length >= 1) {
            // 막대 바 렌더링
            renderStickChart();
        }


        // 차트 렌더링
        var myChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: timeData,
                datasets: [{
                    label: "총 맞춘 점수",
                    lineTension: 0.3,
                    backgroundColor: "rgba(78, 115, 223, 0.05)",
                    borderColor: "rgba(78, 115, 223, 1)",
                    pointRadius: 3,
                    pointBackgroundColor: "rgba(78, 115, 223, 1)",
                    pointBorderColor: "rgba(78, 115, 223, 1)",
                    pointHoverRadius: 3,
                    pointHoverBackgroundColor: "rgba(78, 115, 223, 1)",
                    pointHoverBorderColor: "rgba(78, 115, 223, 1)",
                    pointHitRadius: 10,
                    pointBorderWidth: 2,
                    data: totalList,
                }],
            },
            options: {

                event: ['click'],
                maintainAspectRatio: false,
                layout: {
                    padding: {
                        left: 10,
                        right: 25,
                        top: 25,
                        bottom: 0
                    }
                },
                scales: {
                    xAxes: [{
                        time: {
                            unit: 'date'
                        },
                        gridLines: {
                            display: false,
                            drawBorder: false
                        },
                        ticks: {
                            maxTicksLimit: 7
                        }
                    }],
                    yAxes: [{
                        ticks: {
                            maxTicksLimit: 5,
                            padding: 10,
                            // Include a dollar sign in the ticks
                            // callback: function (value, index, values) {
                            //     // return value
                            //     return '날짜' + chartDatas;
                            // }
                        },
                        gridLines: {
                            color: "rgb(234, 236, 244)",
                            zeroLineColor: "rgb(234, 236, 244)",
                            drawBorder: false,
                            borderDash: [2],
                            zeroLineBorderDash: [2]
                        }
                    }],
                },
                legend: {
                    display: false
                },
                tooltips: {
                    events: ['click'],
                    backgroundColor: "rgb(255,255,255)",
                    bodyFontColor: "#858796",
                    titleMarginBottom: 10,
                    titleFontColor: '#6e707e',
                    titleFontSize: 14,
                    borderColor: '#dddfeb',
                    borderWidth: 1,
                    xPadding: 15,
                    yPadding: 15,
                    displayColors: false,
                    intersect: false,
                    mode: 'index',
                    caretPadding: 10,
                    callbacks: {
                        label: function (tooltipItem, chart) {
                            var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
                            return datasetLabel + ': ' + number_format(tooltipItem.yLabel) + '점';
                        }
                    }
                }
            }
        });

        document.getElementById("myAreaChart").onclick = function (evt) {
            var activePoints = myChart.getElementsAtEvent(evt);

            if (activePoints.length > 0) {
                var clickedElementindex = activePoints[0]["_index"];

                let clickData = ascdata[clickedElementindex];
                let fin = clickData.financeCorrect;
                let finTotal = clickData.financeTotal;
                let trans = clickData.transactionCorrect;
                let transTotal = clickData.transactionTotal;
                let match = clickData.matchCardCorrect;
                let matchTotal = clickData.matchCardTotal;
                let same = clickData.samePictureCorrect;
                let sameTotal = clickData.samePictureTotal;

                let orientWidth = Math.round((fin / finTotal) * 100);
                resultData1.style = `width: ${orientWidth}%`;
                resultData1Text.innerText = orientWidth + "%";
                let attentionWidth = Math.round((trans / transTotal) * 100);
                resultData2.style = `width: ${attentionWidth}%`;
                resultData2Text.innerText = attentionWidth + "%";
                let spacetimeWidth = Math.round((match / matchTotal) * 100);
                resultData3.style = `width: ${spacetimeWidth}%`;
                resultData3Text.innerText = spacetimeWidth + "%";
                let executiveWidth = Math.round((same / sameTotal) * 100);
                resultData4.style = `width: ${executiveWidth}%`;
                resultData4Text.innerText = executiveWidth + "%";

                document.getElementById("clickToChangeDateTime").innerText = clickData.registrationDateString + " 결과";
            }
        }


        // 페이징처리 렌더링
        $(function () {
            (function (name) {
                var container = $('#pagination-' + name);
                if (!container.length) return;
                let jsonData = paginationData.data;
                let descData = jsonData.sort(descCompareEvents);


                var options = {
                    dataSource: descData,

                    pageSize: 4,
                    callback: function (response, pagination) {
                        var dataHtml = '<table class="table table-bordered overflow-auto" id="dataTable" width="100%"cellSpacing="0">';
                        dataHtml += '<thead>';
                        dataHtml += '<tr>';
                        dataHtml += '<th>' + '순서' + '</th>';
                        dataHtml += '<th>' + '맞춘점수' + '</th>';
                        dataHtml += '<th>' + '전체점수' + '</th>';
                        dataHtml += '<th>' + '기록시간' + '</th>';
                        dataHtml += '<th>' + '세부정보' + '</th>';
                        dataHtml += '</tr>';
                        dataHtml += '</thead>';
                        $.each(response, function (index, item) {
                            dataHtml += '<tbody>';
                            dataHtml += '<tr>';
                            dataHtml += '<th>' + (Number(index + 1) + ((pagination.pageNumber - 1) * pagination.pageSize)) + '</th>';
                            dataHtml += '<th>' + item.correctCount + '</th>';
                            dataHtml += '<th>' + item.totalCount + '</th>';
                            dataHtml += '<th>' + item.registrationDateString + '</th>';
                            dataHtml += '<th>' + `<a href=/view/game/result/${item.id}` + '>상세보기</a>' + '</th>';
                            dataHtml += '</tr>';
                            dataHtml += '</tbody>';

                        });

                        dataHtml += '</table>';
                        container.prev().html(dataHtml);
                    }
                };

                //$.pagination(container, options);

                container.addHook('beforeInit', function () {
                    // window.console && console.log('beforeInit...');
                });
                container.pagination(options);

                container.addHook('beforePageOnClick', function () {
                    // window.console && console.log('beforePageOnClick...');
                    //return false
                });
            })('demo1');

        })
    }
}




getDataFromResultPagination();