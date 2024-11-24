package com.example.saebackend.domain.users;

/**
 * A read-only model representing user data.
 * This model is used for transferring user data in a read-only format.
 *
 * @param id The unique identifier of the user.
 * @param name The first name of the user.
 * @param lastname The last name of the user.
 * @param email The email address of the user.
 * @param age The age of the user.
 * @param phoneNumber The phone number of the user.
 * @param moreInformations Additional information about the user.
 */
public record UserReadModel(
        String id,
        String name,
        String lastname,
        String email,
        int age,
        String phoneNumber,
        String moreInformations
){}
