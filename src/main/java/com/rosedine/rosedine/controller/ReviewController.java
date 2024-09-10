package com.rosedine.rosedine.controller;

import com.rosedine.rosedine.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController extends BaseController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/{menuItemId}")
    public ResponseEntity<String> sendReview(@RequestParam int userId, @PathVariable int menuItemId, @RequestParam int stars) {
        try {
            reviewService.sendReview(userId, menuItemId, stars);
            return handleSuccess("Review sent successfully");
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @GetMapping("/{menuItemId}/user-rating")
    public ResponseEntity<Integer> getUserRatingForMenuItem(@RequestParam int userId, @PathVariable int menuItemId) {
        try {
            Integer rating = reviewService.getUserRatingForMenuItem(userId, menuItemId);
            return handleSuccess(rating);
        } catch (Exception e) {
            return handleException(e);
        }
    }
}