document.getElementById("login-btn").addEventListener("click", loginTest)


async function loginTest() {
    let response = await fetch("/api/v1/auth/signin", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            username: document.getElementById("username").value,
            password: document.getElementById("password").value
        })
    })
    if (response.ok) {
        let json = await response.json();
        location.replace("/view/index");
    } else {
        let json = await response.json();
        toastErrorMessage(json.body.result);
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