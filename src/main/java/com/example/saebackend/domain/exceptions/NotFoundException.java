package com.example.saebackend.domain.exceptions;

import java.text.MessageFormat;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }

    public static NotFoundException propertyNotFound(String id) {
        return new NotFoundException(MessageFormat.format("Property with id {0} not found", id));
    }

    public static NotFoundException userNotFound(String id) {
        return new NotFoundException(MessageFormat.format("User with id {0} not found", id));
    }
}
