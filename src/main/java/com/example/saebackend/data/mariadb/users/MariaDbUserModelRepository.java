package com.example.saebackend.data.mariadb.users;

import com.example.saebackend.data.mariadb.jpa_repositories.JpaUserRepository;
import com.example.saebackend.domain.exceptions.NotFoundException;
import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.users.UserInputModel;
import com.example.saebackend.domain.users.UserModel;
import com.example.saebackend.repositories.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of the {@link UserRepository} interface using MariaDB.
 * <p>
 * This repository manages CRUD operations for user-related data using
 * a JPA repository and maps between database entities and domain models.
 * </p>
 */
@Primary
@Repository
public class MariaDbUserModelRepository implements UserRepository {

    private final JpaUserRepository jpaUserRepository;
    private final UserModelMapper userModelMapper;

    /**
     * Constructs a new {@code MariaDbUserModelRepository}.
     *
     * @param jpaUserRepository the JPA repository for performing database operations.
     */
    @Autowired
    public MariaDbUserModelRepository(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
        this.userModelMapper = new UserModelMapper(jpaUserRepository);
    }

    /**
     * Creates a new user in the database.
     *
     * @param userModel the user to be created.
     * @return the created user.
     */
    @Override
    public UserModel create(UserModel userModel) {
        UserModelEntity userModelEntity = userModelMapper.mapFrom(userModel);
        return userModelMapper.mapTo(jpaUserRepository.save(userModelEntity));
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve.
     * @return the user with the specified ID.
     * @throws NotFoundException if the user is not found.
     */
    @Override
    public UserModel getById(Id id) {
        try {
            return userModelMapper.mapTo(jpaUserRepository.getReferenceById(id.toString()));
        } catch (EntityNotFoundException e) {
            throw NotFoundException.propertyNotFound(id.toString());
        }
    }

    /**
     * Retrieves all users from the database.
     *
     * @return a list of all users.
     */
    @Override
    public List<UserModel> getAll() {
        List<UserModelEntity> userEntities = jpaUserRepository.findAll();
        return userEntities.stream().map(userModelMapper::mapTo).toList();
    }

    /**
     * Updates a user in the database.
     *
     * @param id   the ID of the user to update.
     * @param user the updated user data.
     * @return the updated user.
     */
    @Override
    public UserModel update(Id id, UserInputModel user) {
        UserModel userModelToUpdate = getById(id);
        userModelToUpdate.updateFromModel(user);
        UserModelEntity entity = userModelMapper.mapFrom(userModelToUpdate);
        return userModelMapper.mapTo(entity);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete.
     * @return {@code true} if the deletion was successful.
     */
    @Override
    public boolean deleteById(Id id) {
        jpaUserRepository.deleteById(id.toString());
        return true;
    }

    /**
     * Retrieves a user by their email address.
     *
     * @param email the email address of the user to retrieve.
     * @return the user with the specified email.
     * @throws NotFoundException if no user with the specified email is found.
     */
    @Override
    public UserModel getByMail(String email) {
        UserModelEntity userModelEntity = jpaUserRepository.findBymail(email);
        if (userModelEntity == null) {
            throw NotFoundException.propertyNotFound(email);
        }
        return userModelMapper.mapTo(userModelEntity);
    }

    /**
     * Updates the user's password in the database.
     *
     * @param userModel the user whose password is to be updated.
     */
    @Override
    public void forgotPassword(UserModel userModel) {
        UserModelEntity entity = userModelMapper.mapFrom(userModel);
        jpaUserRepository.save(entity);
    }
}
