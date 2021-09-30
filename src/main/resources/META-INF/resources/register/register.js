const URL = 'http://localhost:8080';

function createUser(){
    const user = {};
    user['firstName'] = document.getElementById('firstname').value;
    user['lastName'] = document.getElementById('lastname').value;
    user['username'] = document.getElementById('username').value;
    user['password'] = document.getElementById('password').value;

    fetch(`${URL}/users`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    }).then((result) => {
        result.json().then((user) => {
            console.log(user);
        });
    });
};