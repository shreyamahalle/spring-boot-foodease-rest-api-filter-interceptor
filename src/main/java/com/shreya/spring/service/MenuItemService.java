package com.shreya.spring.service;

import com.shreya.spring.model.MenuItem;

import java.sql.SQLException;
import java.util.List;

public interface MenuItemService {

    boolean addMenuItem(MenuItem menuItem) throws SQLException;

    List<MenuItem> getAllMenuItems();

    MenuItem getMenuItemById(long id);

    boolean deleteMenuItem(long id);

    boolean updateMenuItem(MenuItem menuItem);
}
