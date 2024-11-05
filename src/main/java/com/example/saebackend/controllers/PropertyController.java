package com.example.saebackend.controllers;

import com.example.saebackend.model.id.Id;
import com.example.saebackend.model.property.Property;
import com.example.saebackend.services.property.PropertyHandler;
import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/property")
public class PropertyController {
    private final PropertyHandler propertyHandler;
    private final Gson gson;

    public PropertyController(PropertyHandler propertyHandler) {
        this.propertyHandler = propertyHandler;
        this.gson = new Gson();
    }

    //TODO: Implement controller methods (create, update, delete, etc.)

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createProperty(@RequestBody Property property) {
        Object createdProperty = propertyHandler.create(property);
        return ResponseEntity.ok(createdProperty);
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateProperty(@RequestBody Property property) {
        Object updatedProperty = propertyHandler.update(property);
        return ResponseEntity.ok(updatedProperty);
    }

    @PostMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteProperty(@PathVariable String id) {
        Id idObj = new Id(id);
        Object deletedProperty = propertyHandler.delete(idObj);
        return ResponseEntity.ok(deletedProperty);
    }

    @PostMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllProperties() {
        Object allProperties = propertyHandler.getAll();
        return ResponseEntity.ok(allProperties);
    }

    @PostMapping(value = "/getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPropertyById(@PathVariable String id) {
        Id idObj = new Id(id);
        Property property = propertyHandler.getById(idObj);
        return ResponseEntity.ok(property);
    }


}
