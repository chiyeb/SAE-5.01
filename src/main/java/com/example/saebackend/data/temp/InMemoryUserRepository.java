package com.example.saebackend.data.temp;

import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.users.UserInputModel;
import com.example.saebackend.domain.users.UserModel;
import com.example.saebackend.repositories.user.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import com.example.saebackend.domain.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private final ArrayList<UserModel> users = new ArrayList<>();

    @Override
    public UserModel create(UserModel userModel) {
        users.add(userModel);
        return userModel;
    }

    @Override
    public UserModel getById(Id id) {
        for(UserModel user : users) {
            if (Objects.equals(user.getId(), id)) {
                return user;
            }
        }
        throw NotFoundException.userNotFound(id.toString());
    }

    @Override
    public List<UserModel> getAll() {
        return users;
    }

    @Override
    public UserModel update(Id id, UserInputModel userModel) {
        for(UserModel user : users) {
            if (user.getId().equals(id)) {
                UserModel userToUpdate = this.getById(id);
                userToUpdate.updateFromModel(userModel);
                return users.set(users.indexOf(user), userToUpdate);
            }
        }
        throw NotFoundException.userNotFound(id.toString());
    }

    @Override
    public boolean deleteById(Id id) {
        boolean userif = users.removeIf(userModel -> userModel.getId().equals(id));
        System.out.println(userif);
        return userif;
    }

    @Override
    public UserModel getByEmail(String email) {
        for(UserModel userModel : users){
            if(Objects.equals(userModel.getMail(), email)){
                return userModel;
            }
        }
        throw NotFoundException.userNotFound(email);
    }

    @Override
    public void forgotPassword(UserModel userModel) {
        UserModel user = this.getByEmail(userModel.getMail());
        user.setPassword(userModel.getPassword());
        users.set(users.indexOf(user), user);
    }
}
