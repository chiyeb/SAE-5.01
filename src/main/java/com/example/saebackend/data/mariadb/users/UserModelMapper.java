package com.example.saebackend.data.mariadb.users;

import com.example.saebackend.base.Mapper;
import com.example.saebackend.data.mariadb.jpa_repositories.JpaUserRepository;
import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.users.Password;
import com.example.saebackend.domain.users.UserModel;

public class UserModelMapper implements Mapper<UserModelEntity, UserModel> {

    private final JpaUserRepository jpaUserRepository;

    public UserModelMapper(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public UserModel mapTo(UserModelEntity input) {
        return new UserModel(Id.fromString(input.getId()), input.getName(), input.getLastname(), input.getMail(), input.getAge(), input.getPhoneNumber(), input.getMoreInformations(), input.getPassword());
    }

    @Override
    public UserModelEntity mapFrom(UserModel input) {
        System.out.println(input.getPassword());
        return jpaUserRepository.saveAndFlush(new UserModelEntity(input.getId().toString(), input.getName(), input.getLastname(), input.getMail(), input.getAge(), input.getPhoneNumber(), input.getMoreInformations(), input.getPassword()));
    }
}
