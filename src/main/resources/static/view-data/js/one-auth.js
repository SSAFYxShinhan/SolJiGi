document.getElementById("one-auth-btn").addEventListener("click", oneAuth)
document.getElementById("getTransAction").addEventListener("click", getTransaction);
document.getElementById("accountConfig").addEventListener("click", oneAuthCheck);


function getTransaction() {
    Toastify({
        text: "입금된 KEY는 1 입니다.\n 안녕하세요",
        duration: 3000,
        // destination: "https://github.com/apvarun/toastify-js",
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

        Toastify({
            text: "입금되었습니다.",
            duration: 3000,
            destination: "https://github.com/apvarun/toastify-js",
            newWindow: true,
            close: true,
            gravity: "top", // `top` or `bottom`
            position: "left", // `left`, `center` or `right`
            stopOnFocus: true, // Prevents dismissing of toast on hover
            style: {
                background: "linear-gradient(to right, #00b09b, #96c93d)",
            },
            onClick: function () {
            } // Callback after click
        }).showToast();
        //     Game view 화면
    } else {
        console.log(response);
        let json = await response.text();
        console.log(json)
    }

}

async function oneAuthCheck() {
    let url = "/api/v1/auth/one";
    let response = await fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            accountNumber: 1
            // configKey: document.getElementById("configKey").value

        })
    })
    if (response.ok) {
        console.log(response);
        let json = await response.json();

        let btnList = [];
        btnList.push(document.getElementById("sign-up-btn"));
        btnList.push(document.getElementById("quizBtn"));
        btnList.push(document.getElementById("diagnosisBtn"));

        for (const elementBtn of btnList) {
            if (elementBtn != null) {
                elementBtn.disabled = false;
            }
        }

        //     Game view 화면
    } else {
        console.log(response);
        let json = await response.text();
        console.log(json)
    }

}