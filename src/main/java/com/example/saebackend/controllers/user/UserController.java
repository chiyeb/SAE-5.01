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

/**
 * REST Controller for managing user-related operations.
 * <p>
 * Provides endpoints for creating, retrieving, updating, and deleting users,
 * as well as resetting passwords.
 * </p>
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final Gson gson;

    /**
     * Constructor to initialize {@link UserService} and {@link Gson}.
     *
     * @param userService the user service to handle business logic.
     * @param gson the Gson instance for JSON serialization/deserialization.
     */
    @Autowired
    public UserController(UserService userService, Gson gson) {
        this.userService = userService;
        this.gson = gson;
    }

    /**
     * Creates a new user.
     *
     * @param userInputModel the input data for creating a user.
     * @return a JSON representation of the created user or an error message.
     */
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createUser(@RequestBody UserInputModel userInputModel) {
        if (userInputModel == null) {
            return ResponseEntity.badRequest().body("User is null");
        }
        try {
            return ResponseEntity.ok(gson.toJson(userService.create(userInputModel)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User could not be created: " + e);
        }
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id the ID of the user to retrieve.
     * @return a JSON representation of the user or an error message if not found.
     */
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

    /**
     * Retrieves all users.
     *
     * @return a JSON representation of all users.
     */
    @GetMapping(value = "/get/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllUsers() {
        return ResponseEntity.ok(gson.toJson(userService.getAll()));
    }

    /**
     * Updates a user by ID.
     *
     * @param id the ID of the user to update.
     * @param userInputModel the new data for the user.
     * @return a JSON representation of the updated user.
     */
    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateRentalProperty(@PathVariable String id, @RequestBody UserInputModel userInputModel) {
        try {
            return ResponseEntity.ok(gson.toJson(userService.update(id, userInputModel)));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
        }
    }

    /**
     * Deletes a user by ID.
     *
     * @param id the ID of the user to delete.
     * @return a success message or an error message if the user is not found.
     */
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

    /**
     * Resets the password for a user identified by their email.
     *
     * @param email the email address of the user.
     * @return a success message or an error message if the user is not found.
     */
    @PostMapping(value = "/forgotPassword/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> forgotPassword(@PathVariable String email) {
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
