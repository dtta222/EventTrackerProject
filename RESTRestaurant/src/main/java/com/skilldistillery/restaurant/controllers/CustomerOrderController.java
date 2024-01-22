package com.skilldistillery.restaurant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.skilldistillery.restaurant.data.CustomerOrderDAO;
import com.skilldistillery.restaurant.entities.CustomerOrder;
import com.skilldistillery.restaurant.entities.CustomerOrderItem;
import com.skilldistillery.restaurant.entities.Menu;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class CustomerOrderController {

    @Autowired
    private CustomerOrderDAO customerOrderDAO;

    @PostMapping
    public ResponseEntity<CustomerOrder> createOrder(@RequestBody CustomerOrder order) {
        CustomerOrder createdOrder = customerOrderDAO.createOrder(order);
        if (createdOrder != null) {
            return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<CustomerOrder> getOrderById(@PathVariable(name = "orderId") int orderId) {
        CustomerOrder order = customerOrderDAO.getOrderById(orderId);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    @GetMapping("/all")
    public ResponseEntity<List<CustomerOrder>> getAllOrders() {
        List<CustomerOrder> orders = customerOrderDAO.getAllOrders();
        
        if (orders != null && !orders.isEmpty()) {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{orderId}/status")
    public ResponseEntity<CustomerOrder> updateOrderStatus(@PathVariable int orderId, @RequestParam String status) {
        CustomerOrder updatedOrder = customerOrderDAO.updateOrderStatus(orderId, status);
        if (updatedOrder != null) {
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable int orderId) {
        boolean deleted = customerOrderDAO.deleteOrderById(orderId);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*// Endpoint to add an item to an existing order
    @PostMapping("/{orderId}/items")
    public ResponseEntity<CustomerOrder> addItemToOrder(@PathVariable int orderId, @RequestBody Menu menuItem) {
        CustomerOrder updatedOrder = customerOrderDAO.addItemToOrder(orderId, menuItem);
        if (updatedOrder != null) {
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Endpoint to get items in an order
    @GetMapping("/{orderId}/items")
    public ResponseEntity<List<Menu>> getItemsInOrder(@PathVariable int orderId) {
        List<Menu> items = customerOrderDAO.getItemsInOrder(orderId);
        if (items != null && !items.isEmpty()) {
            return new ResponseEntity<>(items, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/
}
