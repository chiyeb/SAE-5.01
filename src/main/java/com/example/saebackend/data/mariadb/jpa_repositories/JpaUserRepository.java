package com.example.saebackend.data.mariadb.jpa_repositories;

import com.example.saebackend.data.mariadb.users.UserModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<UserModelEntity, String> {
    UserModelEntity findBymail(String email);
}
