package com.rosedine.rosedine.controller;

import com.rosedine.rosedine.service.EmailService;
import com.rosedine.rosedine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Map<String, String> user) {
        try {
            if (user == null || !user.containsKey("email") || !user.containsKey("password")
                    || !user.containsKey("fname") || !user.containsKey("lname")) {
                return handleBadRequest("Invalid request body");
            }

            String fname = user.get("fname");
            String lname = user.get("lname");
            String email = user.get("email");
            String password = user.get("password");

            if (email.isEmpty() || password.isEmpty() || fname.isEmpty() || lname.isEmpty()) {
                return handleBadRequest("All fields must be filled");
            }

            if (userService.userExists(email)) {
                return ResponseEntity.status(409).body("User already exists");
            }

            String verificationToken = emailService.sendVerificationEmail(email);
            return handleSuccess("Please verify your email. A verification code has been sent to " + email + ". Token: " + verificationToken);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @PostMapping("/verify-email")
    public ResponseEntity<String> verifyEmail(@RequestBody Map<String, String> request) {
        try {
            String token = request.get("token");
            String code = request.get("code");
            String fname = request.get("fname");
            String lname = request.get("lname");
            String email = request.get("email");
            String password = request.get("password");

            if (emailService.validateVerificationToken(token, code)) {
                userService.createUser(fname, lname, email, password);
                return handleSuccess("Email verified successfully and user created.");
            } else {
                return handleBadRequest("Invalid or expired token or code.");
            }
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Map<String, String> user) {
        try {
            if (user == null || !user.containsKey("email") || !user.containsKey("password")) {
                return handleBadRequest("Invalid request body");
            }

            String email = user.get("email");
            String password = user.get("password");

            int userId = userService.validateUser(email, password);

            if (userId != -1) {
                return handleSuccess(String.valueOf(userId));
            } else {
                return ResponseEntity.status(401).body("Invalid email or password");
            }
        } catch (Exception e) {
            return handleException(e);
        }
    }
}