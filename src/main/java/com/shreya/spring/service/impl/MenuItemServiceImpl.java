package com.shreya.spring.service.impl;

import com.shreya.spring.model.MenuItem;
import com.shreya.spring.repository.MenuItemRepository;
import com.shreya.spring.service.MenuItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    private final Logger log = LoggerFactory.getLogger(MenuItemServiceImpl.class);

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Override
    public boolean addMenuItem(MenuItem menuItem) throws SQLException {
        log.info("Saving menuItem for customerId {}", menuItem);
        return menuItemRepository.addMenuItem(menuItem);
    }

    @Override
    public List<MenuItem> getAllMenuItems() {
        log.info("all menuItem");
        return menuItemRepository.retrieveMenuItems();
    }

    @Override
    public MenuItem getMenuItemById(long id) {
        log.info("get Menu Item ById {}", id);
        return menuItemRepository.findById(id);
    }

    @Override
    public boolean deleteMenuItem(long id) {
        log.info("delete MenuItem {}", id);
        return menuItemRepository.deleteMenuItem(id);
    }

    @Override
    public boolean updateMenuItem(MenuItem menuItem) {
        log.info("update MenuItem  {}", menuItem);
        return menuItemRepository.updateMenuItem(menuItem);
    }
}
