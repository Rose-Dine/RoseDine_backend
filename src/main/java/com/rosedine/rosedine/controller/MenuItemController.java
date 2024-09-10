package com.rosedine.rosedine.controller;

import com.rosedine.rosedine.dto.MenuItemDTO;
import com.rosedine.rosedine.service.CachedMenuItemService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController extends BaseController {
    private final CachedMenuItemService cachedMenuItemService;

    public MenuItemController(CachedMenuItemService cachedMenuItemService) {
        this.cachedMenuItemService = cachedMenuItemService;
    }

    @GetMapping
    public ResponseEntity<List<MenuItemDTO>> getMenuItemsByDateAndType(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam("type") String type) {
        try {
            List<MenuItemDTO> menuItems = cachedMenuItemService.getMenuItemsByDateAndType(date, type);
            return handleSuccess(menuItems);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<MenuItemDTO>> getAllMenuItems() {
        try {
            List<MenuItemDTO> allMenuItems = cachedMenuItemService.getAllMenuItems();
            return handleSuccess(allMenuItems);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @GetMapping("/check-exists")
    public ResponseEntity<Boolean> checkItemExists(
            @RequestParam("name") String name) {
        try {
            boolean exists = cachedMenuItemService.checkItemExists(name);
            return handleSuccess(exists);
        } catch (Exception e) {
            return handleException(e);
        }
    }
}