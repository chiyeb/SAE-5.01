package com.example.saebackend.data.mariadb.jpa_repositories;

import com.example.saebackend.data.mariadb.users.UserModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository interface for managing {@link UserModelEntity} entities.
 * <p>
 * This interface extends {@link JpaRepository}, providing built-in CRUD operations
 * and the ability to define custom query methods for user data stored in a MariaDB database.
 * </p>
 */
@Repository
public interface JpaUserRepository extends JpaRepository<UserModelEntity, String> {

    /**
     * Finds a {@link UserModelEntity} by its email address.
     *
     * @param email the email address to search for.
     * @return the {@link UserModelEntity} associated with the given email, or {@code null} if no user is found.
     */
    UserModelEntity findBymail(String email);
}
