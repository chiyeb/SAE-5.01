package com.example.saebackend.domain.users;

import com.example.saebackend.domain.id.Id;

//TODO: Implement UserModel
public class UserModel {

    private Id id;
    private String name;
    private String lastname;
    private String email;
    private String age;
    private String phoneNumber;
    private String moreInformations;
    private Password password;

    //================================================================================
    // Constructor
    //================================================================================
    private UserModel(String name, String lastname, String email, String age, String phoneNumber, String moreInformations) {
        this.id = Id.generate();
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.moreInformations = moreInformations;
        this.password = Password.generate();
    }

    //================================================================================
    // Methods
    //================================================================================

    public static UserModel createFromModel(UserInputModel userInputModel){
        return new UserModel(userInputModel.name(), userInputModel.lastname(), userInputModel.email(), userInputModel.age(), userInputModel.phoneNumber(), userInputModel.moreInformations());
    }

    public UserReadModel readModel() {
        return new UserReadModel(id.toString(), name, lastname, email, age, phoneNumber, moreInformations);
    }

    //================================================================================
    // Getters
    //================================================================================

    public Id getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMoreInformations() {
        return moreInformations;
    }

    public Password getPassword() {
        return password;
    }
}
