console.log('orders.js loaded');

let ordersList = [];

window.addEventListener('load', function (evt) {
    console.log('Page loaded, DOM complete');
    init();
});

function init() {
    loadOrders();
}

function loadOrders() {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', '/api/orders/all');
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                ordersList = JSON.parse(xhr.responseText);
                console.log(ordersList);
                displayOrders(ordersList);
            }
        }
    };
    xhr.send();
}

function displayOrders(orders) {
	console.log(orders);

    let tbody = document.getElementById('ordersBody');
    tbody.innerHTML = '';

    for (let order of orders) {
        let tr = document.createElement('tr');
        tbody.appendChild(tr);

        let td = document.createElement('td');
        td.textContent = order.id;
        tr.appendChild(td);

        td = document.createElement('td');
        td.textContent = order.customerID;
        tr.appendChild(td);

        td = document.createElement('td');
        td.textContent = order.tableID;
        tr.appendChild(td);

        td = document.createElement('td');
        td.textContent = order.totalAmount;
        tr.appendChild(td);

        td = document.createElement('td');
        td.textContent = order.status;
        tr.appendChild(td);
    }
}
