package com.rosedine.rosedine.controller;

import com.rosedine.rosedine.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController extends BaseController {
    private final RecommendationService recommendationService;

    @Autowired
    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getRecommendations(@RequestParam int userId, @RequestParam String mealType, @RequestParam String date) {
        try {
            List<Map<String, Object>> recommendations = recommendationService.getRecommendations(userId, mealType, date);
            return handleSuccess(recommendations);
        } catch (Exception e) {
            return handleException(e);
        }
    }
}