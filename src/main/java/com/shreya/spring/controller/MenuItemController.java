package com.shreya.spring.controller;

import com.shreya.spring.exception.MenuItemCreationException;
import com.shreya.spring.exception.MenuItemDeletionException;
import com.shreya.spring.exception.MenuItemNotFoundException;
import com.shreya.spring.exception.MenuItemUpdateException;
import com.shreya.spring.model.MenuItem;
import com.shreya.spring.service.MenuItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/menuItemManagement")
public class MenuItemController {

    private final Logger log = LoggerFactory.getLogger(MenuItemController.class);

    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @PostMapping("/menuItem")
    public ResponseEntity<String> addMenuItem(@RequestBody MenuItem menuItem) {
        try {
            log.info("Received request to add menu item: {}", menuItem);
            menuItemService.addMenuItem(menuItem);
            return ResponseEntity.ok("MenuItem added successfully");
        } catch (MenuItemCreationException | SQLException e) {
            log.error("MenuItem creation failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/menuItem")
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        log.info("Received request to fetch all menu items");
        return ResponseEntity.ok(menuItemService.getAllMenuItems());
    }

    @GetMapping("/menuItem/{id}")
    public ResponseEntity<?> getMenuItemById(@PathVariable long id) {
        try {
            log.info("Received request to fetch menu item by id: {}", id);
            MenuItem menuItem = menuItemService.getMenuItemById(id);
            return ResponseEntity.ok(menuItem);
        } catch (MenuItemNotFoundException e) {
            log.error("MenuItem not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/menuItem/{id}")
    public ResponseEntity<String> deleteMenuItem(@PathVariable long id) {
        try {
            log.info("Received request to delete menu item with id: {}", id);
            menuItemService.deleteMenuItem(id);
            return ResponseEntity.ok("MenuItem deleted successfully");
        } catch (MenuItemDeletionException e) {
            log.error("MenuItem deletion failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/menuItem/{id}")
    public ResponseEntity<String> updateMenuItem(@PathVariable long id, @RequestBody MenuItem menuItem) {
        try {
            menuItem.setId(id);
            log.info("Received request to update menu item: {}", menuItem);
            menuItemService.updateMenuItem(menuItem);
            return ResponseEntity.ok("MenuItem updated successfully");
        } catch (MenuItemUpdateException e) {
            log.error("MenuItem update failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
