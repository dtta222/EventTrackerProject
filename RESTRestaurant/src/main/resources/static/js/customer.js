console.log('customer.js loaded');

function createCustomer() {
    let firstName = document.getElementById('firstName').value;
    let lastName = document.getElementById('lastName').value;
    let email = document.getElementById('email').value;
    let phone = document.getElementById('phone').value;

    let customerData = {
        firstName: firstName,
        lastName: lastName,
        email: email,
        phone: phone
    };

    let xhr = new XMLHttpRequest();
    xhr.open('POST', '/api/customers/new_customer');
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onreadystatechange = function () {
    if (xhr.readyState === 4) {
        console.log('Response status:', xhr.status);
        console.log('Response text:', xhr.responseText);

        if (xhr.status === 200 || xhr.status === 201) {
            console.log('Customer created successfully');
            // You can redirect or perform additional actions upon successful creation
            window.location.href = 'place_order.html';
        } else {
            console.error('Failed to create customer');
        }
    }
};
    xhr.send(JSON.stringify(customerData));
}