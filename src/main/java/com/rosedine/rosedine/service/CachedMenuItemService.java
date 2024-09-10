package com.rosedine.rosedine.service;

import com.rosedine.rosedine.dto.MenuItemDTO;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CachedMenuItemService implements MenuItemService {
    private static final Logger logger = LoggerFactory.getLogger(CachedMenuItemService.class);
    private final MenuItemService menuItemService;
    private final Map<String, List<MenuItemDTO>> cache;
    private final Map<String, Boolean> existenceCache;

    public CachedMenuItemService(MenuItemServiceImpl menuItemService) {
        this.menuItemService = menuItemService;
        this.cache = new ConcurrentHashMap<>();
        this.existenceCache = new ConcurrentHashMap<>();
    }

    @Override
    public List<MenuItemDTO> getMenuItemsByDateAndType(LocalDate date, String type) {
        String key = date.toString() + "-" + type.toLowerCase();
        return cache.computeIfAbsent(key, k -> {
            logger.info("Cache miss for key: {}", k);
            return menuItemService.getMenuItemsByDateAndType(date, type);
        });
    }

    @Override
    public List<MenuItemDTO> getAllMenuItems() {
        return cache.computeIfAbsent("all", k -> {
            logger.info("Cache miss for all items");
            return menuItemService.getAllMenuItems();
        });
    }

    @Override
    public boolean checkItemExists(String name) {
        return existenceCache.computeIfAbsent(name.toLowerCase(), k -> {
            logger.info("Existence cache miss for key: {}", k);
            boolean exists = menuItemService.checkItemExists(k);
            logger.info("Item exists: {} for key: {}", exists, k);
            return exists;
        });
    }

    public void clearCache() {
        logger.info("Clearing cache");
        cache.clear();
        existenceCache.clear();
    }
}