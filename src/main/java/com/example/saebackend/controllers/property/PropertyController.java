package com.example.saebackend.controllers.property;

import com.example.saebackend.domain.exceptions.NotFoundException;
import com.example.saebackend.domain.properties.purchasable.models.PurchasablePropertyInputModel;
import com.example.saebackend.domain.properties.rental.models.RentalPropertyInputModel;
import com.example.saebackend.services.JWTService;
import com.example.saebackend.services.property.PurchasablePropertyService;
import com.example.saebackend.services.property.RentalPropertyService;
import com.google.gson.Gson;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * Controller for handling property-related requests.
 * Provides endpoints for creating, retrieving, updating, and deleting purchasable and rental properties.
 */
@RestController
@RequestMapping("/property")
public class PropertyController {
    private final PurchasablePropertyService purchasablePropertyService;
    private final RentalPropertyService rentalPropertyService;
    private final JWTService jwtService;
    private final Gson gson;

    @Autowired
    public PropertyController(PurchasablePropertyService purchasablePropertyService, RentalPropertyService rentalPropertyService, JWTService jwtService) {
        this.purchasablePropertyService = purchasablePropertyService;
        this.rentalPropertyService = rentalPropertyService;
        this.jwtService = jwtService;
        this.gson = new Gson();
    }

    @PostMapping(value = "/create/purchasable", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createPurchasableProperty(@Valid @RequestBody PurchasablePropertyInputModel propertyModel, @RequestHeader("Authorization") String authHeader) {
        if (propertyModel == null) {
            return ResponseEntity.badRequest().body("Property is null");
        }
        String id = jwtService.getLoggedUserId(authHeader.replace("Bearer ", ""));
        return ResponseEntity.ok(gson.toJson(purchasablePropertyService.create(propertyModel, id)));
    }

    @GetMapping(value = "/get/purchasable/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getPurchasablePropertyById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(gson.toJson(purchasablePropertyService.getById(id)));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found", e);
        }
    }

    @GetMapping(value = "/get/purchasable", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllPurchasableProperties() {
        return ResponseEntity.ok(gson.toJson(purchasablePropertyService.getAll()));
    }

    @PutMapping(value = "/update/purchasable/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updatePurchasableProperty(@PathVariable String id, @Valid @RequestBody PurchasablePropertyInputModel propertyModel) {
        try {
            return ResponseEntity.ok(gson.toJson(purchasablePropertyService.update(id, propertyModel)));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found", e);
        }
    }

    @DeleteMapping(value = "/delete/purchasable/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> deletePurchasableProperty(@PathVariable String id) {
        try {
            purchasablePropertyService.deleteById(id);
            return ResponseEntity.ok("Purchasable property with ID: " + id + " deleted successfully");
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found", e);
        }
    }

    @PostMapping(value = "/create/rental", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createRentalProperty(@Valid @RequestBody RentalPropertyInputModel propertyModel, @RequestHeader("Authorization") String authHeader) {
        if (propertyModel == null) {
            return ResponseEntity.badRequest().body("Property is null");
        }
        String id = jwtService.getLoggedUserId(authHeader.replace("Bearer ", ""));
        return ResponseEntity.ok(gson.toJson(rentalPropertyService.create(propertyModel, id)));
    }

    @GetMapping(value = "/get/rental/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getRentalPropertyById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(gson.toJson(rentalPropertyService.getById(id)));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found", e);
        }
    }

    @GetMapping(value = "/get/rental", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllRentalProperties() {
        return ResponseEntity.ok(gson.toJson(rentalPropertyService.getAll()));
    }

    @PutMapping(value = "/update/rental/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateRentalProperty(@PathVariable String id,@Valid @RequestBody RentalPropertyInputModel propertyModel) {
        try {
            return ResponseEntity.ok(gson.toJson(rentalPropertyService.update(id, propertyModel)));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found", e);
        }
    }

    @DeleteMapping(value = "/delete/rental/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> deleteRentalProperty(@PathVariable String id) {
        try {
            rentalPropertyService.deleteById(id);
            return ResponseEntity.ok("Rental property with ID: " + id + " deleted successfully");
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found", e);
        }
    }
    @GetMapping(value = "/get/rental/owner", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getRentalPropertyByOwnerId(@RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.replace("Bearer ", "");
            String idOwer = jwtService.getLoggedUserId(token);
            return ResponseEntity.ok(gson.toJson(rentalPropertyService.getByOwnerId(idOwer)));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found", e);
        }
    }
    @GetMapping(value = "/get/purchasable/owner", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getPurchasablePropertyByOwnerId(@RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.replace("Bearer ", "");
            String idOwer = jwtService.getLoggedUserId(token);
            return ResponseEntity.ok(gson.toJson(purchasablePropertyService.getByOwnerId(idOwer)));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found", e);
        }
    }
}
