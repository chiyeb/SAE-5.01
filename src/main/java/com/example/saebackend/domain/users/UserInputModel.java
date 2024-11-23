package com.example.saebackend.domain.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UserInputModel(
        @NotNull
        String name,
        @NotNull
        String lastname,
        @NotNull
        @Email
        String email,
        @Positive
        int age,
        @NotNull
        String phoneNumber,
        @NotNull
        String moreInformations) {}