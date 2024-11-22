package com.example.saebackend.domain.users;

//TODO: Implement UserReadModel
public record UserReadModel(
        String id,
        String name,
        String lastname,
        String email,
        int age,
        String phoneNumber,
        String moreInformations
){}
