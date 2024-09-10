package com.rosedine.rosedine.service;

import com.rosedine.rosedine.dto.MenuItemDTO;
import com.rosedine.rosedine.repository.MenuItemRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

@Service
public class MenuItemServiceImpl implements MenuItemService {
    private static final Logger logger = LoggerFactory.getLogger(MenuItemServiceImpl.class);
    private final MenuItemRepository menuItemRepository;

    public MenuItemServiceImpl(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public List<MenuItemDTO> getMenuItemsByDateAndType(LocalDate date, String type) {
        return menuItemRepository.getMenuItemsByDateAndType(date, type);
    }

    @Override
    public List<MenuItemDTO> getAllMenuItems() {
        return menuItemRepository.getAllMenuItems();
    }

    @Override
    public boolean checkItemExists(String name) {
        logger.info("Checking existence of item: name={}", name);
        boolean exists = menuItemRepository.checkItemExists(name);
        logger.info("Item exists: {}", exists);
        return exists;
    }
}