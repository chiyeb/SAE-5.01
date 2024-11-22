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
import org.springframework.stereotype.Service;
import com.example.saebackend.domain.exceptions.NotFoundException;

import java.util.List;

//TODO: Implement UserService
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserReadModel create(UserInputModel userInputModel) {
        String plainPassword = Password.generatePassword();
        UserModel userModelToCreate = UserModel.createFromModel(userInputModel, plainPassword);
        UserModel userModel = userRepository.create(userModelToCreate);
        if (userModel != null) {
            MailSender.sendPasswordEmail(userModel.getMail(), userModel.getName(), plainPassword);
            return userModel.readModel();
        }
        return null;
    }

    public UserReadModel getById(String id) {
        UserModel userModel = userRepository.getById(Id.fromString(id));
        if (userModel == null) throw NotFoundException.userNotFound(id);
        return userModel.readModel();
    }

    public List<UserReadModel> getAll() {
        return userRepository.getAll().stream().map(UserModel::readModel).toList();
    }

    public UserReadModel update(String id, UserInputModel userInputModel) {
        UserModel userModel = userRepository.update(Id.fromString(id), userInputModel);
        if (userModel == null) throw NotFoundException.userNotFound(id);
        return userModel.readModel();
    }

    public void deleteById(String id) {
        UserModel userModel = userRepository.getById(Id.fromString(id));
        if (!userRepository.deleteById(Id.fromString(id))) throw NotFoundException.userNotFound(id);
        MailSender.sendAccountDeletionConfirmation(userModel.getMail(), userModel.getName());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getByMail(username);
    }

    public void forgotPassword(String email) {
        //TODO: LA FONCTION NE MARCHE PAS, IL FAUT UPDATE LE MDP DANS LA DB + ENVOYER UN MAIL
        UserModel userModel = userRepository.getByMail(email);
        if (userModel == null) throw NotFoundException.userNotFound(email);
        userModel.setPassword(Password.generatePassword());
        userRepository.update(userModel.getId(), new UserInputModel(userModel.getName(), userModel.getLastname(), userModel.getMail(), userModel.getAge(), userModel.getPhoneNumber(), userModel.getMoreInformations()));
        MailSender.sendPasswordEmail(userModel.getMail(), userModel.getName(), userModel.getPassword());
    }
}
