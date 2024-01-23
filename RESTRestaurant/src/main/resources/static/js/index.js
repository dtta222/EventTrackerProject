console.log('index.js loaded');

let menuList = []; // To store the menu items

window.addEventListener('load', function (evt) {
    console.log('Page loaded, DOM complete');
    init();
});

function init() {
    loadMenu();
}

function loadMenu() {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', '/api/menu/items');
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                menuList = JSON.parse(xhr.responseText);
                console.log(menuList);
                displayMenu(menuList);
            }
        }
    };
    xhr.send();
}

function displayMenu(menu) {
    let tbody = document.getElementById('listBody');
    tbody.innerHTML = ''; // Clear previous content

    for (let item of menu) {
        let tr = document.createElement('tr');
        tbody.appendChild(tr);

        let td = document.createElement('td');
        td.textContent = item.id;
        tr.appendChild(td);

        td = document.createElement('td');
        td.textContent = item.name;
        tr.appendChild(td);

        td = document.createElement('td');
        td.textContent = item.price;
        tr.appendChild(td);
    }
}


function navigateToCustomers() {
    window.location.href = '/customers.html';
}


function navigateToOrders() {
    window.location.href = '/orders.html';
}


