package com.example.saebackend.controllers.user;

import com.example.saebackend.domain.exceptions.NotFoundException;
import com.example.saebackend.domain.properties.rental.models.RentalPropertyInputModel;
import com.example.saebackend.domain.users.UserInputModel;
import com.example.saebackend.services.user.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


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


    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUserById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(gson.toJson(userService.getById(id)));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
        }
    }


    @PostMapping(value = "/get/all", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllUsers() {
        return ResponseEntity.ok(gson.toJson(userService.getAll()));
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateRentalProperty(@PathVariable String id, @RequestBody UserInputModel userInputModel) {
        try {
            return ResponseEntity.ok(gson.toJson(userService.update(id, userInputModel)));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
        }
    }

    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteUser(@RequestBody String id) {
        try {
            userService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
        }
    }
}
