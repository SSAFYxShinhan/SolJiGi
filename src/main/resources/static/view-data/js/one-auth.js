document.getElementById("one-auth-btn").addEventListener("click", oneAuth)

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
        location.replace("/view/sign-in");
        //     Game view 화면
    } else {
        console.log(response);
        let json = await response.text();
        console.log(json)
    }

}