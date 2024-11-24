package com.example.saebackend.data.inmemory.users;

import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.users.UserInputModel;
import com.example.saebackend.domain.users.UserModel;
import com.example.saebackend.repositories.user.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import com.example.saebackend.domain.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * In-memory implementation of the UserRepository interface.
 * Provides methods for managing users stored in memory.
 */
@Repository
public class InMemoryUserRepository implements UserRepository {
    private final ArrayList<UserModel> users = new ArrayList<>();


    public InMemoryUserRepository() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        users.add(new UserModel(Id.generate(),"Administrateur", "IMPULSE", "impulsewordpresssae@alwaysdata.net", 999, "123456789", "Je suis l'admin !", bCryptPasswordEncoder.encode("password")));
    }

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
    public UserModel getByMail(String mail) {
        return users.stream().filter(userModel -> userModel.getMail().equals(mail)).findFirst().orElseThrow();
    }

    @Override
    public List<UserModel> getAll() {
        return users;
    }

    @Override
    public UserModel update(Id id, UserInputModel userModel) {
        for(UserModel user : users) {
            if (user.getId().equals(id)) return user.updateFromModel(userModel);
        }
        throw NotFoundException.userNotFound(id.toString());
    }

    @Override
    public boolean deleteById(Id id) {
        return users.removeIf(userModel -> userModel.getId().equals(id));
    }

    @Override
    public void forgotPassword(UserModel userModel) {
        UserModel user = this.getByMail(userModel.getMail());
        user.setPassword(userModel.getPassword());
        users.set(users.indexOf(user), user);
    }
}
