console.log('place_order.js loaded');

let menuList = []; // To store the menu items
let orderList = []; // To store the selected items in the order

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

        td = document.createElement('td');
        let addButton = document.createElement('button');
        addButton.textContent = 'Add to Order';
        addButton.onclick = function () {
            addToOrder(item);
        };
        td.appendChild(addButton);
        tr.appendChild(td);
    }
}

function displayOrder(order) {
    let tbody = document.getElementById('orderBody');
    tbody.innerHTML = ''; // Clear previous content

    for (let item of order) {
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

        td = document.createElement('td');
        let removeButton = document.createElement('button');
        removeButton.textContent = 'Remove';
        removeButton.onclick = function () {
            removeFromOrder(item);
        };
        td.appendChild(removeButton);
        tr.appendChild(td);
    }
}

function addToOrder(item) {
    orderList.push(item);
    displayOrder(orderList);
}

function removeFromOrder(item) {
    orderList = orderList.filter(i => i.id !== item.id);
    displayOrder(orderList);
}

function placeOrder() {
    console.log('Placing order:', orderList);

    // Assuming your server endpoint for creating orders is "/api/orders"
    fetch('/api/orders', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            // You may need to adjust this based on your server's expected order format
            customerID: 1,  // Replace with the actual customer ID
            tableID: 1,     // Replace with the actual table ID
            // Add other necessary properties based on your CustomerOrder entity
            items: orderList.map(item => ({
                itemId: item.id,
                quantity: 1,  // You may adjust the quantity as needed
            })),
        }),
    })
    .then(response => {
        if (response.ok) {
            console.log('Order placed successfully!');
            // Reset the orderList after placing the order
            orderList = [];
            displayOrder(orderList);
            // You can add additional logic or redirection if needed
        } else {
            console.error('Failed to place order:', response.status);
            // Handle the error accordingly
        }
    })
    .catch(error => {
        console.error('Error placing order:', error);
        // Handle the error accordingly
    });
}


/*function placeOrder() {
    console.log('Placing order:', orderList);
    // Reset the orderList after placing the order
    orderList = [];
    displayOrder(orderList);

    // Navigate to index.html
    //navigateToIndex();
}*/

function navigateToIndex() {
    window.location.href = '/index.html';
}

