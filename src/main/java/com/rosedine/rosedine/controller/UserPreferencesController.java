package com.rosedine.rosedine.controller;

import com.rosedine.rosedine.service.UserPreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user-preferences")
public class UserPreferencesController extends BaseController {

    @Autowired
    private UserPreferencesService userPreferencesService;

    @PutMapping("/update-dietary-restriction")
    public ResponseEntity<String> updateDietaryRestriction(@RequestParam("userId") int userId,
                                                           @RequestParam("restrictionName") String restrictionName,
                                                           @RequestParam("restrictionValue") boolean restrictionValue) {
        try {
            userPreferencesService.updateDietaryRestriction(userId, restrictionName, restrictionValue);
            return handleSuccess("Dietary restriction updated successfully");
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @PutMapping("/update-macro")
    public ResponseEntity<String> updateMacro(@RequestParam("userId") int userId,
                                              @RequestParam("mealType") String mealType,
                                              @RequestParam("macroName") String macroName,
                                              @RequestParam("macroValue") int macroValue) {
        try {
            userPreferencesService.updateMacro(userId, mealType, macroName, macroValue);
            return handleSuccess("Macro updated successfully");
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @GetMapping("/get-preferences")
    public ResponseEntity<Map<String, Object>> getUserPreferences(@RequestParam("userId") int userId) {
        try {
            Map<String, Object> preferences = userPreferencesService.getUserPreferences(userId);
            return handleSuccess(preferences);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @GetMapping("/get-all-preferences")
    public ResponseEntity<Map<String, Object>> getAllPreferences(@RequestParam("userId") int userId, @RequestParam String mealType) {
        try {
            Map<String, Object> preferences = userPreferencesService.getAllUserPreferences(userId, mealType);
            return handleSuccess(preferences);
        } catch (Exception e) {
            return handleException(e);
        }
    }
}