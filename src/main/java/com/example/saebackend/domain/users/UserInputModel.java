package com.example.saebackend.domain.users;

public record UserInputModel(
        String name,
        String lastname,
        String email,
        int age,
        String phoneNumber,
        String moreInformations) {}