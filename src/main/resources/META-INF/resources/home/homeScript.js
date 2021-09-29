if (localStorage.getItem("token") === null) {
    location.href = "/login/login.html";
    console.log(localStorage.getItem("token"));
}
console.log(localStorage.getItem("token"))

const URL = 'http://localhost:8080';
let entries = [];

const dateAndTimeToDate = (dateString, timeString) => {
    return new Date(`${dateString}T${timeString}`).toISOString();
};

const createEntry = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const entry = {};
    entry['checkIn'] = dateAndTimeToDate(formData.get('checkInDate'), formData.get('checkInTime'));
    entry['checkOut'] = dateAndTimeToDate(formData.get('checkOutDate'), formData.get('checkOutTime'));
    entry['sickLeave'] = checkboxToString(formData.get('sickLeave'));


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

const createReason = (e) => {
    e.preventDefault();
    const sickLeave = {};
    const formData = new FormData(e.target);
    sickLeave['reason'] = formData.get('reason');

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
    if (bol) {
        return "true";
    } else {
        return "false";
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
        //row.appendChild(createCell(bolToString(entry.sickLeave)));
        display.appendChild(row);
    });
};

document.addEventListener('DOMContentLoaded', function () {
    const createEntryForm = document.querySelector('#createEntryForm');
    createEntryForm.addEventListener('submit', createEntry);
    indexEntries();
});