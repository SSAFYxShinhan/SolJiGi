document.getElementById("one-auth-btn").addEventListener("click", oneAuth)
document.getElementById("getTransAction").addEventListener("click", getTransaction);
document.getElementById("accountConfig").addEventListener("click", oneAuthCheck);


let key = "";

/**
 * url : 1원인증 조회 api
 * 조회하여 결과값을 key 로 저장
 * @returns {Promise<void>}
 */
async function getTransaction() {
    let url = ""
    let response = await fetch(url);
    if (response.ok) {
        let json = response.json();
        // json response를 text
        toastSuccessMessage("성공")
    }else{
        let json = response.json();
        // error 코드를 입력
        toastErrorMessage("에러");
    }
}

/**
 * url : 1원 인증 요청 서비스
 * 실제 DB 1원 코드와 거래내역을 남긴다.
 * @returns {Promise<void>}
 */
async function oneAuth() {
    let response = await fetch("/api/v1/auth/one", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            accountNumber: document.getElementById("account-number-input").value
        })
    })
    if (response.ok) {
        console.log(response);
        let json = await response.json();
        toastSuccessMessage("1원이 입금되었습니다");
    } else {
        console.log(response);
        let json = await response.text();
        console.log(json)
    }

}

/**
 * url:
 * 거래내역 1개를 조회하는 api 활용하여 코드를 확인함
 * @returns {Promise<void>}
 */
async function oneAuthCheck() {
    let inputKey = document.getElementById("configKey").value;
    if(inputKey === key){
        toastSuccessMessage("인증성공");
        let btnList = [];
        btnList.push(document.getElementById("sign-up-btn"));
        btnList.push(document.getElementById("quizBtn"));
        btnList.push(document.getElementById("diagnosisBtn"));
        for (const elementBtn of btnList) {
            if (elementBtn != null) {
                elementBtn.disabled = false;
            }
        }
    }else{
        toastErrorMessage("잘못된 정보");
    }


}

function toastSuccessMessage(text){
    Toastify({
        text: text,
        duration: 3000,
        destination: "https://github.com/apvarun/toastify-js",
        newWindow: true,
        close: true,
        gravity: "top", // `top` or `bottom`
        position: "center", // `left`, `center` or `right`
        stopOnFocus: true, // Prevents dismissing of toast on hover
        style: {
            background: "linear-gradient(to right, #00b09b, #96c93d)",
        },
        onClick: function () {
        } // Callback after click
    }).showToast();
}
function toastErrorMessage(text){
    Toastify({
        text: text,
        duration: 3000,
        newWindow: true,
        close: true,
        gravity: "top", // `top` or `bottom`
        position: "center", // `left`, `center` or `right`
        stopOnFocus: true, // Prevents dismissing of toast on hover
        style: {
            background: "linear-gradient(to right, #F5515F, #A1051D)",
        },
        onClick: function () {
        } // Callback after click
    }).showToast();
}