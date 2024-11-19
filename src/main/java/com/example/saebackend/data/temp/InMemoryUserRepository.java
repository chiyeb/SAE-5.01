package com.example.saebackend.data.temp;

import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.users.UserModel;
import com.example.saebackend.repositories.user.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
        for (UserModel user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<UserModel> getAll() {
        return users;
    }

    @Override
    public UserModel update(String id, UserModel userModel) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().toString().equals(id)) {
                users.set(i, userModel);
                return userModel;
            }
        }
        return null;
    }

    @Override
    public boolean deleteById(Id id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.remove(i);
                return true;
            }
        }
        return false;
    }
}
