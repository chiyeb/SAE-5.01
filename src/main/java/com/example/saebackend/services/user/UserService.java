package com.example.saebackend.services.user;

import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.users.UserInputModel;
import com.example.saebackend.domain.users.UserModel;
import com.example.saebackend.domain.users.UserReadModel;
import com.example.saebackend.repositories.user.UserRepository;
import org.springframework.stereotype.Service;
import com.example.saebackend.domain.exceptions.NotFoundException;

import java.util.List;

//TODO: Implement UserService
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserReadModel create(UserInputModel userInputModel) {
        return userRepository.create(UserModel.createFromModel(userInputModel)).readModel();
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
        UserModel userModel = userRepository.update(id, UserModel.createFromModel(userInputModel));
        if (userModel == null) throw NotFoundException.userNotFound(id);
        return userModel.readModel();
    }

    public void deleteById(String id) {
        if (!userRepository.deleteById(Id.fromString(id))) throw NotFoundException.userNotFound(id);
    }
}
