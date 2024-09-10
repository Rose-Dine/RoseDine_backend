package com.rosedine.rosedine.controller;

import com.rosedine.rosedine.service.JsonDataService;
import com.rosedine.rosedine.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menus")
public class MenuController extends BaseController {

    private final JsonDataService jsonDataService;

    public MenuController(@Autowired MenuService menuService, @Autowired JsonDataService jsonDataService) {
        this.jsonDataService = jsonDataService;
    }

    @PostMapping("/import")
    public ResponseEntity<String> importJsonData() {
        try {
            jsonDataService.parseAndSaveJsonData();
            return handleSuccess("JSON data imported successfully.");
        } catch (Exception e) {
            return handleException(e);
        }
    }
}