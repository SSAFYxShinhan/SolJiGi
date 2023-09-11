document.getElementById("sign-up-btn").addEventListener("click", joinTest);
document.getElementById("one-auth-btn").addEventListener("click", oneAuth)
document.getElementById("one-auth-check-btn").addEventListener("click", oneAuthCheck)

async function joinTest() {
    let response = await fetch("/api/v1/auth/signup", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            username: document.getElementById("username").value,
            password: document.getElementById("password").value,
            email: document.getElementById("email").value,
            grade: document.getElementById("grade").value,
            parentNumber: document.getElementById("parent-number").value,
            gender: document.getElementById("gender").value,
            birth: document.getElementById("birth").value,
            address: document.getElementById("address").value,
            phoneNumber: document.getElementById("phone-number").value,
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


async function oneAuth() {
    let response = await fetch("/api/v1/auth/one", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            accountNumber: document.getElementById("account-number").value
        })
    })
    if (response.ok) {
        console.log(response);
        let json = await response.json();
        // location.replace("/view/sign-in");
        //     Game view 화면
    } else {
        console.log(response);
        let json = await response.text();
        console.log(json)
    }

}

async function oneAuthCheck() {
    let response = await fetch("/api/v1/auth/one", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            accountNumber: document.getElementById("account-number").value
        })
    })
    if (response.ok) {
        console.log(response);
        let json = await response.json();
        // location.replace("/view/sign-in");
        //     Game view 화면
    } else {
        console.log(response);
        let json = await response.text();
        console.log(json)
    }
}