/**
 * getDataFromResultPagination(); 페이징 처리, 포인트 그래프 렌더링
 * renderStickChart(); 막대바 렌더링
 *
 */

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

function renderStickChart() {
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


async function getDataFromResultPagination() {
    let url = "/diagnosis/data"
    let response = await fetch(url);
    let paginationData;
    if (response.ok) {
        let json = await response.json();
        let paginationData = json;
        totalList = json.data.map(e => e.totalScore);
       // let size = Math.min(30, totalList.length);
        //totalList = totalList.slice(0,size);
        let timeData = json.data.map(e => e.registrationDateString);
       // timeData = timeData.slice(0,size);

        if (json.data[0] != null) {
            let currentDateTime = json.data[0].doneInMonth;
            if (currentDateTime) {
                document.getElementById("monthIsDone").innerText = "완료"
            } else {
                document.getElementById("monthIsDone").innerText = "미완료"
            }
        }else{
            document.getElementById("monthIsDone").innerText = "미완료"
        }
        json.data.map(e => {
            orientList.push(e.orientScore);
            attentionList.push(e.attentionScore);
            spacetimeList.push(e.spacetimeScore);
            executiveList.push(e.executiveScore);
            languageList.push(e.languageScore);
            memoryList.push(e.memoryScore);
        });

        // 막대 바 렌더링
        renderStickChart();

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

        document.getElementById("myAreaChart").onclick = function(evt){
            var activePoints = myChart.getElementsAtEvent(evt);

            if(activePoints.length > 0)
            {
                var clickedElementindex = activePoints[0]["_index"];
                console.log(clickedElementindex);
                let clickData = json.data[clickedElementindex];


                let orientData = clickData.orientScore;
                let attentionData = clickData.attentionScore;
                let spacetimeData = clickData.spacetimeScore;
                let executiveData = clickData.executiveScore;
                let languageData = clickData.languageScore;
                let memoryData = clickData.memoryScore;



                let orientWidth = Math.round((orientData / orient ) * 100);
                resultData1.style = `width: ${orientWidth}%`;
                resultData1Text.innerText = orientWidth;
                let attentionWidth = Math.round((attentionData / attention) * 100);
                resultData2.style = `width: ${attentionWidth}%`;
                resultData2Text.innerText = attentionWidth;
                let spacetimeWidth = Math.round((spacetimeData / spacetime) * 100);
                resultData3.style = `width: ${spacetimeWidth}%`;
                resultData3Text.innerText = spacetimeWidth;
                let executiveWidth = Math.round((executiveData / executive) * 100);
                resultData4.style = `width: ${executiveWidth}%`;
                resultData4Text.innerText = executiveWidth;
                let languageWidth = Math.round((languageData / language) * 100)
                resultData5.style = `width: ${languageWidth}%`;
                resultData5Text.innerText = languageWidth;
                let memoryWidth = Math.round((memoryData / memory) * 100);
                resultData6.style = `width: ${memoryWidth}%`;
                resultData6Text.innerText = memoryWidth;

                document.getElementById("clickToChangeDateTime").innerText = clickData.registrationDateString + " 결과";
            }
        }
        // 페이징처리 렌더링
        $(function () {
            (function (name) {
                var container = $('#pagination-' + name);
                if (!container.length) return;
                let sampleData = paginationData.data.sort((a,b)=>(a.registrationDate - b.registrationDate));

                var options = {
                    dataSource: sampleData,
                    pageSize: 6,
                    callback: function (response, pagination) {
                        // window.console && console.log(response, pagination);

                        var dataHtml = '<table class="table table-bordered overflow-auto" id="dataTable" width="100%"cellSpacing="0">';
                        dataHtml += '<thead>';
                        dataHtml += '<tr>';
                        dataHtml += '<th>' + '순서' + '</th>';
                        dataHtml += '<th>' + '결과타입' + '</th>';
                        dataHtml += '<th>' + '전체점수' + '</th>';
                        dataHtml += '<th>' + '기록시간' + '</th>';
                        dataHtml += '<th>' + '세부정보' + '</th>';
                        dataHtml += '</tr>';
                        dataHtml += '</thead>';
                        $.each(response, function (index, item) {
                            dataHtml += '<tbody>';
                            dataHtml += '<tr>';
                            dataHtml += '<th>' + (Number(index+1) + ((pagination.pageNumber-1) * pagination.pageSize)) + '</th>';
                            dataHtml += '<th>' + item.result + '</th>';
                            dataHtml += '<th>' + item.totalScore + '</th>';
                            dataHtml += '<th>' + item.registrationDateString + '</th>';
                            dataHtml += '<th>' + `<a href=/view/diagnosis/result/${item.id}` + '>상세보기</a>' + '</th>';
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