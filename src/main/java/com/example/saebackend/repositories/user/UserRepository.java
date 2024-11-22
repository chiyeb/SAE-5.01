package com.example.saebackend.repositories.user;

import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.users.UserInputModel;
import com.example.saebackend.domain.users.UserModel;

import java.util.List;

//TODO: Implement UserRepository
public interface UserRepository {
    UserModel create(UserModel userModel);
    UserModel getById(Id id);
    UserModel getByMail(String mail);
    List<UserModel> getAll();
    UserModel update(Id id, UserInputModel userModel);
    boolean deleteById(Id id);
}
