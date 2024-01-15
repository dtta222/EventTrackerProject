package com.skilldistillery.restaurant.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.skilldistillery.restaurant.data.MenuDAO;
import com.skilldistillery.restaurant.entities.Category;
import com.skilldistillery.restaurant.entities.Menu;

@RestController
@RequestMapping("api/menu")
public class MenuController {

    @Autowired
    private MenuDAO menuDAO;

    // add a new menu item
    @PostMapping("/items")
    public ResponseEntity<Menu> addItem(@RequestBody Menu menu) {
        Menu addedItem = menuDAO.addMenuItem(menu);
        if (addedItem != null) {
            return new ResponseEntity<>(addedItem, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // add a new menu category
    @PostMapping("/categories")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        Category addedCategory = menuDAO.addCategory(category);
        if (addedCategory != null) {
            return new ResponseEntity<>(addedCategory, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // get all menu items
    @GetMapping("/items")
    public ResponseEntity<List<Menu>> getAllItems() {
        List<Menu> menuItems = menuDAO.getAllMenuItems();
        if (menuItems != null && !menuItems.isEmpty()) {
            return new ResponseEntity<>(menuItems, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // get details of a specific menu item
    @GetMapping("/items/{itemId}")
    public ResponseEntity<Menu> getItemById(@PathVariable int itemId) {
        Menu menuItem = menuDAO.findMenuItemById(itemId);
        if (menuItem != null) {
            return new ResponseEntity<>(menuItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // delete a menu item by ID
    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<Void> deleteItemById(@PathVariable int itemId) {
        boolean deleted = menuDAO.deleteMenuItemById(itemId);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
