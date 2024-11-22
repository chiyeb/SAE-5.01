package com.example.saebackend.data.mariadb.users;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserModelEntity {

    @Id
    private String id;
    private String name;
    private String lastname;
    private String mail;
    private int age;
    private String phoneNumber;
    private String moreInformations;
    private String password;

    public UserModelEntity() {
    }

    public UserModelEntity(String id, String name, String lastname, String mail, int age, String phoneNumber, String moreInformations, String password) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.mail = mail;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.moreInformations = moreInformations;
        this.password = password;
    }

    public String getId() {
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

    public int getAge() {
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
}
