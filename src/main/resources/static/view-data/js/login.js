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
        console.log(response);
        let json = await response.json();
        location.replace("/view/index");
    } else {
        console.log(response);
        let json = await response.text();
        
    }

}