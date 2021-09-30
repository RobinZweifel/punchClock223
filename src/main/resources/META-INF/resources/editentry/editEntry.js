if (localStorage.getItem("token") === null) {
    location.href = "/login/login.html";
    console.log(localStorage.getItem("token"));
}
console.log(localStorage.getItem("token"))

const URL = 'http://localhost:8080';

const dateAndTimeToDate = (dateString, timeString) => {
    return new Date(`${dateString}T${timeString}`).toISOString();
};


const editEntry = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const entry = {};

    entry['id'] = localStorage.getItem("edit_id");
    entry['checkIn'] = dateAndTimeToDate(formData.get('checkInDate'), formData.get('checkInTime'));
    entry['checkOut'] = dateAndTimeToDate(formData.get('checkOutDate'), formData.get('checkOutTime'));

    console.log(entry);

    fetch(`${URL}/entries`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(entry)
    }).then((result) => {
        location.href = "/home/home.html";
    });
};



function edit(){
    let id = localStorage.getItem("edit_id");
    console.log("LOCALSTORRAGE_ID: " + id)
    fetch(`${URL}/entries`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        }
    });
    indexEntries();
}

document.addEventListener('DOMContentLoaded', function () {
    const createEntryForm = document.querySelector('#createEntryForm');
    createEntryForm.addEventListener('submit', editEntry);
});