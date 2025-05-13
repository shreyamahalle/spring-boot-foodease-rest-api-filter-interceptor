package com.shreya.spring.controller;

import com.shreya.spring.model.MenuItem;
import com.shreya.spring.service.MenuItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/menuItemManagement")
public class MenuItemController {

    private final Logger log = LoggerFactory.getLogger(MenuItemController.class);

    @Autowired
    private MenuItemService menuItemService;

    @PostMapping("/menuItem")
    public boolean addMenuItem(@RequestBody MenuItem menuItem) throws SQLException {
        log.info("Received request to add menu item: {}", menuItem);
        return menuItemService.addMenuItem(menuItem);
    }

    @GetMapping("/menuItem")
    public List<MenuItem> getAllMenuItems() {
        log.info("Received request to fetch all menu items");
        return menuItemService.getAllMenuItems();
    }

    @GetMapping("/menuItem/{id}")
    public MenuItem getMenuItemById(@PathVariable long id) {
        log.info("Received request to fetch menu item by id: {}", id);
        return menuItemService.getMenuItemById(id);
    }

    @DeleteMapping("/menuItem/{id}")
    public boolean deleteMenuItem(@PathVariable long id) {
        log.info("Received request to delete menu item with id: {}", id);
        return menuItemService.deleteMenuItem(id);
    }

    @PutMapping("/menuItem/{id}")
    public boolean updateMenuItem(@PathVariable long id, @RequestBody MenuItem menuItem) {
        menuItem.setId(id);  // Ensure the id is set correctly in the object
        log.info("Received request to update menu item: {}", menuItem);
        return menuItemService.updateMenuItem(menuItem);
    }
}
