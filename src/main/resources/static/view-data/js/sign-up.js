document.getElementById("sign-up-btn").addEventListener("click", joinTest);
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
    let accountNumber = document.getElementById("account-number-input").value
    if (accountNumber == null || accountNumber === "") {
        toastErrorMessage("계좌번호를 입력해주세요");
    }
    let url = "/v1/search/1transfer"
    let response = await fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            dataHeader: {
                apikey: "2023_Shinhan_SSAFY_Hackathon"
            },
            dataBody: {
                "계좌번호": accountNumber
            }
        })
    });
    if (response.ok) {
        let json = await response.json();
        if (json.dataHeader.resultCode == 1) {
            toastErrorMessage("없는 계좌번호입니다.");
        } else {
            console.log(json)
            toastSuccessMessage("최근 거래내역 조회 결과 : " + json.dataBody.입금통장메모);
            document.getElementById("configKey").value = json.dataBody.입금통장메모;

        }
    } else {
        let json = await response.json();
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
    let accountNumber = document.getElementById("account-number-input").value
    if (accountNumber == null || accountNumber === "") {
        toastErrorMessage("계좌번호를 입력해주세요");
    }
    let response = await fetch("/v1/auth/1transfer", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            dataHeader: {
                apikey: "2023_Shinhan_SSAFY_Hackathon"
            },
            dataBody: {
                "입금은행코드": "088",
                "입금계좌번호": accountNumber,
                "입금통장메모": "dummy"
            }
        })
    })
    if (response.ok) {
        console.log(response);
        let json = await response.json();
        if (json.dataHeader.resultCode === "1") {
            toastErrorMessage("없는 계좌번호입니다.");
        } else {
            console.log(json)
            document.getElementById("account-number-input").disabled = true;
            toastSuccessMessage("1원이 입금되었습니다");
        }
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
    let getKey = document.getElementById("configKey").value
    let accountNumber = document.getElementById("account-number-input").value
    if (accountNumber == null || accountNumber === "") {
        toastErrorMessage("계좌번호를 입력해주세요");
    }
    let url = "/v1/search/1transfer"
    let response = await fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            dataHeader: {
                apikey: "2023_Shinhan_SSAFY_Hackathon"
            },
            dataBody: {
                "계좌번호": accountNumber
            }
        })
    });
    if (response.ok) {
        let json = await response.json();
        if (json.dataHeader.resultCode == 1) {
            toastErrorMessage(json.dataBody.입금통장메모);
        } else {
            if (getKey === json.dataBody.입금통장메모) {
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
                getUserData();
            } else {
                toastErrorMessage("잘못된 정보");
            }

        }
    }
}

async function getUserData(){
    let accountNumber = document.getElementById("account-number-input").value;
    let url = "/api/v1/account"
    let response = await fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            accountNumber: accountNumber
        })
    });
    if (response.ok) {
        let json = await response.json();
        console.log(json);
        document.getElementById("name").value = json.result.name;
        if(json.result.gender === "MALE"){
            document.getElementById("male").checked = true;
        }else{
            document.getElementById("female").checked = true;
        }
        document.getElementById("date").value = json.result.birthDate;
        document.getElementById("phone-number").value = json.result.phoneNumber;

        toastSuccessMessage("데이터00");
    }
}

function toastSuccessMessage(text) {
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

function toastErrorMessage(text) {
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

document.addEventListener('DOMContentLoaded', function() {
    let today = new Date();
    let year = today.getFullYear();
    let month = (1 + today.getMonth()).toString().padStart(2, '0'); // month는 0부터 시작합니다.
    let day = today.getDate().toString().padStart(2, '0');

    let formattedDate = `${year}-${month}-${day}`;
    document.getElementById('date').value = formattedDate;
});
async function joinTest() {
    var male = docment.getElementById("male").checked;
    var gender;
    if(male){
        gender = 0;
    }else{
        gender = 1;
    }

    let response = await fetch("/api/v1/auth/signup", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            username: document.getElementById("username").value,
            password: document.getElementById("password").value,
            grade: document.getElementById("grade").value,
            parentNumber: document.getElementById("parent-number").value,
            address:document.getElementById("address_kakao").value,
            gender: gender,
            birth: document.getElementById("birth").value,
            address: document.getElementById("address").value,
            phoneNumber: document.getElementById("phone-number").value,
            name: document.getElementById("name").value,
            accountNumber: document.getElementById("account-number-input").value
        })
    })
    if (response.ok) {
        console.log(response);
        let json = await response.json();
        location.replace("/view/sign-in");
    } else {
        console.log(response);
        let json = await response.text();
        console.log(json)
    }

}

