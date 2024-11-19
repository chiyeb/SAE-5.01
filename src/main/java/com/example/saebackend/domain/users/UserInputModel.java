package com.example.saebackend.domain.users;

public record UserInputModel(
        String name,
        String lastname,
        String email,
        String age,
        String phoneNumber,
        String moreInformations) {}