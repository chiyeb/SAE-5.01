package com.example.saebackend.domain.users;

import com.example.saebackend.domain.id.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 * Represents a user in the system with personal information and a password.
 * This model is used for storing and manipulating user data such as name, email, age, etc.
 */
public class UserModel  implements UserDetails {
    private final Id id;
    private String name;
    private String lastname;
    private String mail;
    private int age;
    private String phoneNumber;
    private String moreInformations;
    private final Set<GrantedAuthority> authorities = new HashSet<>();
    private String password;

    //================================================================================
    // Constructor
    //================================================================================

    /**
     * Creates a new user model with the specified parameters.
     *
     * @param id The unique identifier for the user.
     * @param name The first name of the user.
     * @param lastname The last name of the user.
     * @param mail The email address of the user.
     * @param age The age of the user.
     * @param phoneNumber The phone number of the user.
     * @param moreInformations Additional information about the user.
     * @param password The password of the user.
     */
    public UserModel(Id id, String name, String lastname, String mail, int age, String phoneNumber, String moreInformations, String password) {
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

    /**
     * Creates a UserModel from a UserInputModel and encrypts the password.
     *
     * @param userInputModel The user input model containing user details.
     * @param plainPassword The plain password to be encrypted.
     * @return The created UserModel.
     */
    public static UserModel createFromModel(UserInputModel userInputModel, String plainPassword) {
        return new UserModel(Id.generate(), userInputModel.name(), userInputModel.lastname(), userInputModel.email(), userInputModel.age(), userInputModel.phoneNumber(), userInputModel.moreInformations(), Password.encryptPassword(plainPassword));
    }

    /**
     * Converts the UserModel to a UserReadModel for reading purposes.
     *
     * @return The corresponding UserReadModel.
     */
    public UserReadModel readModel() {
        return new UserReadModel(id.toString(), name, lastname, mail, age, phoneNumber, moreInformations);
    }

    /**
     * Updates the current UserModel from a UserInputModel.
     *
     * @param userInputModel The user input model containing updated details.
     * @return The updated UserModel.
     */
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
    // Getters and Setters
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

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMoreInformations() {
        return moreInformations;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getMail();
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

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setMoreInformations(String moreInformations) {
        this.moreInformations = moreInformations;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
