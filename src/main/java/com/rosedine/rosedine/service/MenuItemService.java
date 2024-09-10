package com.rosedine.rosedine.service;

import com.rosedine.rosedine.dto.MenuItemDTO;
import java.time.LocalDate;
import java.util.List;

public interface MenuItemService {
    List<MenuItemDTO> getMenuItemsByDateAndType(LocalDate date, String type);
    List<MenuItemDTO> getAllMenuItems();
    boolean checkItemExists(String name);
}