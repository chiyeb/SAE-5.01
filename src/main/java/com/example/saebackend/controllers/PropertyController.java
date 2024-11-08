package com.example.saebackend.controllers;

import com.example.saebackend.model.id.Id;
import com.example.saebackend.model.property.Property;
import com.example.saebackend.model.property.purchasable.PurchasableProperty;
import com.example.saebackend.model.property.rental.RentalProperty;
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

    // TODO: FAIRE MARCHER CETTE PUTAIN DE FONCTION DE MERDE | j'arrive pas à trouver réelement le probleme ...
    @PostMapping(value = "/create/rental", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createProperty(@RequestBody RentalProperty property) {
        if (property == null) {
            return ResponseEntity.badRequest().body("Property is null");
        }
        Object createdProperty = propertyHandler.create(property);
        return ResponseEntity.ok(createdProperty);
    }

    @PostMapping(value = "/create/purchasable", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createProperty(@RequestBody PurchasableProperty property) {
        if (property == null) {
            return ResponseEntity.badRequest().body("Property is null");
        }
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
