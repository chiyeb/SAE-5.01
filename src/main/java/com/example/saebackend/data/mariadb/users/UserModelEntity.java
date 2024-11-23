package com.example.saebackend.data.mariadb.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity class representing a user in the system.
 * This class maps to the "users" table in the database.
 */
@Entity
@Table(name = "user")
public class UserModelEntity {

    @Id
    private String id;
    @Column(name="first_name")
    private String name;
    @Column(name="last_name")
    private String lastname;
    @Column(name="mail")
    private String mail;
    @Column(name="age")
    private int age;
    @Column(name="phone_number")
    private String phoneNumber;
    @Column(name="more_informations")
    private String moreInformations;
    @Column(name="password")
    private String password;

    /**
     * Default constructor for JPA.
     */
    public UserModelEntity() {
    }

    /**
     * Constructs a new {@code UserModelEntity} with the specified details.
     *
     * @param id               the unique identifier of the user.
     * @param name             the first name of the user.
     * @param lastname         the last name of the user.
     * @param mail             the email address of the user.
     * @param age              the age of the user.
     * @param phoneNumber      the phone number of the user.
     * @param moreInformations additional information about the user.
     * @param password         the password of the user.
     */
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

    /**
     * Gets the unique identifier of the user.
     *
     * @return the user's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the first name of the user.
     *
     * @return the user's first name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the last name of the user.
     *
     * @return the user's last name.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Gets the email address of the user.
     *
     * @return the user's email address.
     */
    public String getMail() {
        return mail;
    }

    /**
     * Gets the age of the user.
     *
     * @return the user's age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets the phone number of the user.
     *
     * @return the user's phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Gets additional information about the user.
     *
     * @return more information about the user.
     */
    public String getMoreInformations() {
        return moreInformations;
    }

    /**
     * Gets the password of the user.
     * <p><strong>Note:</strong> Storing passwords in plain text is not secure.
     * Consider using encryption or hashing for sensitive data.</p>
     *
     * @return the user's password.
     */
    public String getPassword() {
        return password;
    }
}
