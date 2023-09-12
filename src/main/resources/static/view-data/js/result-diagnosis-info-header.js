// todayMention 오늘의 한마디
// weekPayCount 주간 결제건수
// monthIsDone 월간 검사/ 게임 유무

let weekPayCountArea = document.getElementById("weekPayCount");

async function getTradeInfo() {
    const url = "";
    let response = await fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({

        })
    })
    if (response.ok) {
        console.log(response);
        let json = await response.json();

        weekPayCountArea.innerText = "카운트횟수";
        //     Game view 화면
    }
}