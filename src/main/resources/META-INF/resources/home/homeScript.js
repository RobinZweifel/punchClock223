if (localStorage.getItem("token") === null) {
    location.href = "/login/login.html";
    console.log(localStorage.getItem("token"));
}
console.log(localStorage.getItem("token"))

const URL = 'http://localhost:8080';
let entries = [];
let leaves = [];

const dateAndTimeToDate = (dateString, timeString) => {
    return new Date(`${dateString}T${timeString}`).toISOString();
};

const createEntry = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const entry = {};
    entry['checkIn'] = dateAndTimeToDate(formData.get('checkInDate'), formData.get('checkInTime'));
    entry['checkOut'] = dateAndTimeToDate(formData.get('checkOutDate'), formData.get('checkOutTime'));
    //entry['sickLeave'] = checkboxToString(document.getElementById('sickLeaveCheckbox').value);


    fetch(`${URL}/entries`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(entry)
    }).then((result) => {
        result.json().then((entry) => {
            entries.push(entry);
            renderEntries();
        });
    });
};

function logOut(){
    localStorage.setItem("token", "");
    console.log(localStorage.getItem("token"));
    window.location.replace("http://localhost:8080/login/login.html");
}



function deleteEntry(id){
    fetch(`${URL}/entries/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-type': 'Application/json'
        }
    })
}

const createLeave = (e) => {
    e.preventDefault();
    const sickLeave = {};
    const formData = new FormData(e.target);
    sickLeave['reason'] = formData.get('reason');

    fetch(`${URL}/leaves`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(sickLeave)
    }).then((result) => {
        result.json().then((sickLeave) => {
            leaves.push(sickLeave);
        });
    });
}

window.onload = () => {

    console.log(document.querySelector("#sickLeaveCheckbox"));

    document.querySelector("#sickLeaveCheckbox").addEventListener("change", (event) => {
        let checked = document.querySelector("#sickLeaveCheckbox").checked;
        console.log("sickleave: ", checked);
        console.log(event)
        if (checked) {
            document.querySelector("#sickLeaveData").style.display = "block";
        } else {
            console.log("none")
            document.querySelector("#sickLeaveData").style.display = "none";
        }
    })
}


const checkboxToString = (bol) => {
    console.log("Bevore: " + bol);
    if (bol === "on") {
        bol = "Krank"
        console.log("After: " + bol);
        return bol;
    } else {
        bol = "Gesund"
        console.log("After: " + bol);
        return bol;
    }
}

const indexEntries = () => {
    fetch(`${URL}/entries`, {
        method: 'GET'
    }).then((result) => {
        result.json().then((result) => {
            entries = result;
            renderEntries();
        });
    });
    renderEntries();
};

const createCell = (text) => {
    const cell = document.createElement('td');
    cell.innerText = text;
    return cell;
};

const bolToString = (bol) => {
    if (bol === true) {
        return 'Krank';
    } else {
        return 'Gesund';
    }
}

const renderEntries = () => {
    const display = document.querySelector('#entryDisplay');
    display.innerHTML = '';
    entries.forEach((entry) => {
        const row = document.createElement('tr');
        row.appendChild(createCell(entry.id));
        row.appendChild(createCell(new Date(entry.checkIn).toLocaleString()));
        row.appendChild(createCell(new Date(entry.checkOut).toLocaleString()));
        //console.log("entry.sickleave" + entry.sickLeave);
        //row.appendChild(createCell(entry.sickLeave));

        let btnDelete = document.createElement('button');
        btnDelete.innerText = "delete";
        btnDelete.onclick = () => {
            deleteEntry(entry.id);
        }
        row.appendChild(btnDelete);
        display.appendChild(row);
    });
};

document.addEventListener('DOMContentLoaded', function () {
    const createEntryForm = document.querySelector('#createEntryForm');
    createEntryForm.addEventListener('submit', createEntry);
    indexEntries();
});