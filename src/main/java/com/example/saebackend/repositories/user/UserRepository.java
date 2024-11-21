package com.example.saebackend.repositories.user;

import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.users.UserInputModel;
import com.example.saebackend.domain.users.UserModel;
import org.springframework.stereotype.Repository;

import java.util.List;

//TODO: Implement UserRepository
public interface UserRepository {
    UserModel create(UserModel userModel);
    UserModel getById(Id id);
    List<UserModel> getAll();
    UserModel update(Id id, UserInputModel user);
    boolean deleteById(Id id);
    UserModel getByEmail(String email);
}
