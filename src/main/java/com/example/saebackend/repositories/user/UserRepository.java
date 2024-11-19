package com.example.saebackend.repositories.user;

import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.users.UserModel;
import org.springframework.stereotype.Repository;

import java.util.List;

//TODO: Implement UserRepository
public interface UserRepository {
    UserModel create(UserModel userModel);
    UserModel getById(Id id);
    List<UserModel> getAll();
    UserModel update(String id, UserModel userModel);
    boolean deleteById(Id id);
}
