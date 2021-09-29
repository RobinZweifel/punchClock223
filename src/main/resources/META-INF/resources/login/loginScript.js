const URL = 'http://localhost:8080';
var token = "";

function loginUser() {
    const user = {};
    user['username'] = document.getElementById("login").value;
    user['password'] = document.getElementById("password").value;

    fetch(`${URL}/auth/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    }).then((result) => {
        result.text().then((tokenStr) => {
            console.log(tokenStr);
            token = tokenStr;
            if (token !== ""){
                localStorage.setItem("token", tokenStr);
                console.log(localStorage.getItem("token"));
                window.location.replace("http://localhost:8080/home/home.html")
            }
        });
    });
}