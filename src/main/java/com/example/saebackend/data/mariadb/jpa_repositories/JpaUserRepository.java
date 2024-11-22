package com.example.saebackend.data.mariadb.jpa_repositories;

import com.example.saebackend.data.mariadb.users.UserModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface JpaUserRepository extends JpaRepository<UserModelEntity, String> {
    UserModelEntity findBymail(String email);

//    @Transactional
//    @Modifying
//    @Query("UPDATE UserModelEntity u SET u.password = :newPassword WHERE u.mail = :email")
//    int updatePasswordByEmail(String email, String newPassword);


}
