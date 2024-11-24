package com.example.saebackend.domain.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * A read-only model representing user data.
 * This model is used for transferring user data in a read-only format.
 *
 * @param name The first name of the user.
 * @param lastname The last name of the user.
 * @param email The email address of the user.
 * @param age The age of the user.
 * @param phoneNumber The phone number of the user.
 * @param moreInformations Additional information about the user.
 */
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