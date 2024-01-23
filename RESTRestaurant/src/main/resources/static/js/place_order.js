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
    // Create the order object to send to the server
    const orderObject = {
        customerID: 105,  // Replace with the actual customer ID
        tableID: 5,       // Replace with the actual table ID
        orderDate: "2024-03-01T20:00:00",  // Replace with the actual order date
        totalAmount: calculateTotalAmount(orderList),  // Assuming you have a function to calculate the total amount
        status: "In Progress",  
        serverID: 2,  
        items: orderList.map(item => ({
            id: item.id,
            name: item.name,
            price: item.price,
            description: item.description,  
            category: item.category  
        }))
    };

    // Make a POST request to the server
    fetch('/api/orders', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(orderObject),
    })
    .then(response => {
        if (response.ok) {
            console.log('Order placed successfully!');
            // Reset the orderList after placing the order
            orderList = [];
            displayOrder(orderList);
        } else {
            console.error('Failed to place order:', response.status);
            // Handle the error accordingly
        }
    })
    .catch(error => {
        console.error('Error placing order:', error);
        // Handle the error accordingly
    });
    
    // Navigate to index.html
    navigateToIndex(); 
}

// Function to calculate the total amount based on the items in the order
function calculateTotalAmount(orderItems) {
    return orderItems.reduce((total, item) => total + item.price, 0);
}

function navigateToIndex() {
    window.location.href = '/index.html';
}

