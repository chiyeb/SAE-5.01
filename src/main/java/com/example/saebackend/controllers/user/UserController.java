package com.example.saebackend.controllers.user;

import com.example.saebackend.domain.exceptions.NotFoundException;
import com.example.saebackend.domain.users.UserInputModel;
import com.example.saebackend.services.user.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final Gson gson;

    @Autowired
    public UserController(UserService userService, Gson gson) {
        this.userService = userService;
        this.gson = gson;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createUser(@RequestBody UserInputModel userInputModel) {
        if (userInputModel == null) {
            return ResponseEntity.badRequest().body("User is null");
        }
        return ResponseEntity.ok(gson.toJson(userService.create(userInputModel)));
    }

    @GetMapping("/current")
    public Object getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        try {
            return userService.getLoggedUser(token);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
        }
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(gson.toJson(userService.getById(id)));
        } catch (NotFoundException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "User not found");
            response.put("message", "No user found with ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping(value = "/get/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllUsers() {
        return ResponseEntity.ok(gson.toJson(userService.getAll()));
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody UserInputModel userInputModel) {
        try {
            return ResponseEntity.ok(gson.toJson(userService.update(id, userInputModel)));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
        }
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        try {
            userService.deleteById(id);
            return ResponseEntity.ok("User with ID: " + id + " deleted successfully");
        } catch (NotFoundException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "User not found");
            response.put("message", "No user found with ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping(value = "/forgotPassword/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> forgotPassword(@PathVariable String email){
        try {
            userService.forgotPassword(email);
            return ResponseEntity.ok("Password reset successfully");
        } catch (NotFoundException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "User not found");
            response.put("message", "No user found with this mail address: " + email);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
