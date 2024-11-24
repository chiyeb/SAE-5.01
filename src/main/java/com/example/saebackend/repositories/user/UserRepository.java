package com.example.saebackend.repositories.user;

import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.users.UserInputModel;
import com.example.saebackend.domain.users.UserModel;

import java.util.List;

/**
 * Repository interface for user-related operations.
 * <p>
 * Defines the contract for managing user entities, including CRUD operations
 * and additional user-specific functionalities.
 * </p>
 */
public interface UserRepository {

    /**
     * Creates a new user.
     *
     * @param userModel the {@link UserModel} object containing user details.
     * @return the created {@link UserModel}.
     */
    UserModel create(UserModel userModel);

    /**
     * Retrieves a user by their unique ID.
     *
     * @param id the {@link Id} of the user to retrieve.
     * @return the {@link UserModel} of the requested user.
     */
    UserModel getById(Id id);

    /**
     * Retrieves a user by their email address.
     *
     * @param mail the email address of the user to retrieve.
     * @return the {@link UserModel} of the requested user.
     */
    UserModel getByMail(String mail);


    /**
     * Retrieves all users.
     *
     * @return a {@link List} of all {@link UserModel} objects.
     */
    List<UserModel> getAll();

    /**
     * Updates a user's details.
     *
     * @param id the {@link Id} of the user to update.
     * @param user the {@link UserInputModel} containing updated user details.
     * @return the updated {@link UserModel}.
     */
    UserModel update(Id id, UserInputModel user);

    /**
     * Deletes a user by their unique ID.
     *
     * @param id the {@link Id} of the user to delete.
     * @return {@code true} if the user was deleted successfully, {@code false} otherwise.
     */
    boolean deleteById(Id id);

    /**
     * Resets the password for the specified user.
     *
     * @param userModel the {@link UserModel} for which the password is to be reset.
     */
    void forgotPassword(UserModel userModel);
}
