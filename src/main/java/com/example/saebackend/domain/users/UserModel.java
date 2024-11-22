package com.example.saebackend.domain.users;

import com.example.saebackend.domain.id.Id;

//TODO: Implement UserModel
public class UserModel {

    private Id id;
    private String name;
    private String lastname;
    private String mail;
    private String age;
    private String phoneNumber;
    private String moreInformations;
    private String password;

    //================================================================================
    // Constructor
    //================================================================================
    public UserModel(Id id, String name, String lastname, String mail, String age, String phoneNumber, String moreInformations, String password) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.mail = mail;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.moreInformations = moreInformations;
        this.password = password;
    }

    //================================================================================
    // Methods
    //================================================================================

    public static UserModel createFromModel(UserInputModel userInputModel, String plainPassword) {
        return new UserModel(Id.generate(), userInputModel.name(), userInputModel.lastname(), userInputModel.email(), userInputModel.age(), userInputModel.phoneNumber(), userInputModel.moreInformations(), Password.encryptPassword(plainPassword));
    }

    public UserReadModel readModel() {
        return new UserReadModel(id.toString(), name, lastname, mail, age, phoneNumber, moreInformations);
    }

    public UserModel updateFromModel(UserInputModel userInputModel){
        setName(userInputModel.name());
        setLastname(userInputModel.lastname());
        setMail(userInputModel.email());
        setAge(userInputModel.age());
        setPhoneNumber(userInputModel.phoneNumber());
        setMoreInformations(userInputModel.moreInformations());
        return this;
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

    public String getMail() {
        return mail;
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

    public String getPassword() {
        return password;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setMoreInformations(String moreInformations) {
        this.moreInformations = moreInformations;
    }

}
