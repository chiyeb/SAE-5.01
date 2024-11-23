package com.example.saebackend.services.user;

import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.users.Password;
import com.example.saebackend.domain.users.UserInputModel;
import com.example.saebackend.domain.users.UserModel;
import com.example.saebackend.domain.users.UserReadModel;
import com.example.saebackend.repositories.user.UserRepository;
import com.example.saebackend.services.utils.MailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;
import com.example.saebackend.domain.exceptions.NotFoundException;

import java.util.List;

/**
 * Service class for managing user operations.
 * <p>
 * Provides business logic for user-related actions such as creation, retrieval, update, deletion, and password reset.
 * </p>
 */
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final JwtDecoder jwtDecoder;

    /**
     * Constructor for UserService.
     *
     * @param userRepository the {@link UserRepository} to access user data.
     */
    public UserService(UserRepository userRepository, JwtDecoder jwtDecoder) {
        this.userRepository = userRepository;
        this.jwtDecoder = jwtDecoder;
    }

    /**
     * Creates a new user.
     *
     * @param userInputModel the input model containing user details.
     * @return the created {@link UserReadModel} if successful, otherwise {@code null}.
     * @throws RuntimeException if the user could not be created.
     */
    public UserReadModel create(UserInputModel userInputModel) {
        String plainPassword = Password.generatePassword();
        UserModel userModelToCreate = UserModel.createFromModel(userInputModel, plainPassword);
        try {
            UserModel userModel = userRepository.create(userModelToCreate);
            if (userModel != null) {
                MailSender.sendPasswordEmail(userModel.getMail(), userModel.getName(), plainPassword);
                return userModel.readModel();
            }
        } catch (Exception e) {
            throw new RuntimeException("User could not be created");
        }
        return null;
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve.
     * @return the {@link UserReadModel} of the requested user.
     * @throws NotFoundException if the user is not found.
     */
    public UserReadModel getById(String id) {
        UserModel userModel = userRepository.getById(Id.fromString(id));
        if (userModel == null) throw NotFoundException.userNotFound(id);
        return userModel.readModel();
    }

    public UserReadModel getLoggedUser(String token) {
        return userRepository.getById(Id.fromString(jwtDecoder.decode(token).getSubject())).readModel();
    }

    /**
     * Retrieves all users.
     *
     * @return a list of {@link UserReadModel} objects representing all users.
     */
    public List<UserReadModel> getAll() {
        return userRepository.getAll().stream().map(UserModel::readModel).toList();
    }

    /**
     * Updates an existing user.
     *
     * @param id the ID of the user to update.
     * @param userInputModel the input model containing the updated user details.
     * @return the updated {@link UserReadModel}.
     * @throws NotFoundException if the user is not found.
     */
    public UserReadModel update(String id, UserInputModel userInputModel) {
        UserModel userModel = userRepository.update(Id.fromString(id), userInputModel);
        if (userModel == null) throw NotFoundException.userNotFound(id);
        return userModel.readModel();
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete.
     * @throws NotFoundException if the user is not found.
     */
    public void deleteById(String id) {
        UserModel userModel = userRepository.getById(Id.fromString(id));
        if (!userRepository.deleteById(Id.fromString(id))) throw NotFoundException.userNotFound(id);
        MailSender.sendAccountDeletionConfirmation(userModel.getMail(), userModel.getName());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getByMail(username);
    }


    /**
     * Resets the password for the user associated with the given email.
     *
     * @param email the email address of the user whose password is to be reset.
     * @throws NotFoundException if the user is not found.
     */
    public void forgotPassword(String email) {
        UserModel userModel = userRepository.getByMail(email);
        if (userModel == null) throw NotFoundException.userNotFound(email);
        String newPassword = Password.generatePassword();
        userModel.setPassword(Password.encryptPassword(newPassword));
        userRepository.forgotPassword(userModel);
        MailSender.sendResetPassword(userModel.getMail(), userModel.getName(), newPassword);
    }
}
