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

@Primary
@Repository
public class MariaDbUserModelRepository implements UserRepository {

    private final JpaUserRepository jpaUserRepository;
    private final UserModelMapper userModelMapper;

    @Autowired
    public MariaDbUserModelRepository(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
        this.userModelMapper = new UserModelMapper(jpaUserRepository);

    }
    @Override
    public UserModel create(UserModel userModel) {
        UserModelEntity userModelEntity = userModelMapper.mapFrom(userModel);
        return userModelMapper.mapTo(jpaUserRepository.save(userModelEntity));
    }

    @Override
    public UserModel getById(Id id) {
        try {
            return userModelMapper.mapTo(jpaUserRepository.getReferenceById(id.toString()));
        }
        catch(EntityNotFoundException e) {
            throw NotFoundException.propertyNotFound(id.toString());
        }
    }

    @Override
    public List<UserModel> getAll() {
        List<UserModelEntity> userEntities = jpaUserRepository.findAll();
        return userEntities.stream().map(userModelMapper::mapTo).toList();
    }

    @Override
    public UserModel update(Id id, UserInputModel user) {
        UserModel userModelToUpdate = getById(id);
        userModelToUpdate.updateFromModel(user);
        UserModelEntity entity = userModelMapper.mapFrom(userModelToUpdate);
        return userModelMapper.mapTo(entity);
    }

    @Override
    public boolean deleteById(Id id) {
            jpaUserRepository.deleteById(id.toString());
            return true;
    }

    @Override
    public UserModel getByEmail(String email) {
        UserModelEntity userModelEntity = jpaUserRepository.findBymail(email);
        if (userModelEntity == null) {
            throw NotFoundException.propertyNotFound(email);
        }
        return userModelMapper.mapTo(userModelEntity);
    }
}
