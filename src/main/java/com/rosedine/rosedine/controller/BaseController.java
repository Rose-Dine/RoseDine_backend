package com.rosedine.rosedine.controller;

import org.springframework.http.ResponseEntity;

public abstract class BaseController {

    protected <T> ResponseEntity<T> handleException(Exception e) {
        return ResponseEntity.status(500).body(null);
    }

    protected <T> ResponseEntity<T> handleBadRequest(String message) {
        return ResponseEntity.badRequest().body(null);
    }

    protected <T> ResponseEntity<T> handleSuccess(T body) {
        return ResponseEntity.ok(body);
    }
}