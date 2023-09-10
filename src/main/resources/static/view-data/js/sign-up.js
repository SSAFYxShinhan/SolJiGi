document.getElementById("sign-up-btn").addEventListener("click", joinTest);

async function joinTest() {
    let response = await fetch("/api/v1/auth/signup", {
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
        console.log(response);
        let json = await response.json();
        location.replace("/view/sign-in");
    } else {
        console.log(response);
        let json = await response.text();
        console.log(json)
    }

}