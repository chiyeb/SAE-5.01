package com.example.saebackend.services.property;

import com.example.saebackend.model.id.Id;
import com.example.saebackend.model.property.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropertyHandler implements PropertyHandlerInterface {
    private final PropertyService propertyService;

    @Autowired
    public PropertyHandler(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    //TODO: Implement handle methods (create, update, delete, etc.)
    public Object create(Property property) {
        return propertyService.create(property);
    }

    public Object update(Property property) {
        return propertyService.update(property);
    }

    public Object delete(Id id) {
        return propertyService.delete(id);
    }

    public Property getById(Id id) {
        return propertyService.getById(id);
    }

    public Object getAll() {
        return propertyService.getAll();
    }
}
