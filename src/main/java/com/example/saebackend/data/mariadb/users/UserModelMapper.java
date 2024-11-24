package com.example.saebackend.data.mariadb.users;

import com.example.saebackend.base.Mapper;
import com.example.saebackend.data.mariadb.jpa_repositories.JpaUserRepository;
import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.users.UserModel;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Mapper class for converting between {@link UserModelEntity} and {@link UserModel}.
 * <p>
 * This class handles the transformation of data between the database entity
 * and the domain model for users.
 * </p>
 */
public class UserModelMapper implements Mapper<UserModelEntity, UserModel> {

    private final JpaUserRepository jpaUserRepository;

    /**
     * Constructs a new {@code UserModelMapper} with the specified repository.
     *
     * @param jpaUserRepository the JPA repository used for database operations.
     */
    public UserModelMapper(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    /**
     * Maps a {@link UserModelEntity} to a {@link UserModel}.
     *
     * @param input the {@link UserModelEntity} to be mapped.
     * @return the corresponding {@link UserModel}.
     */
    @Override
    public UserModel mapTo(UserModelEntity input) {
        return new UserModel(
                Id.fromString(input.getId()),
                input.getName(),
                input.getLastname(),
                input.getMail(),
                input.getAge(),
                input.getPhoneNumber(),
                input.getMoreInformations(),
                input.getPassword(),
                input.isAdmin()
        );
    }

    /**
     * Maps a {@link UserModel} to a {@link UserModelEntity} and saves it to the database.
     *
     * @param input the {@link UserModel} to be mapped and saved.
     * @return the saved {@link UserModelEntity}.
     */
    @Override
    public UserModelEntity mapFrom(UserModel input) {
        System.out.println(input.getPassword()); // Debugging statement
        return jpaUserRepository.saveAndFlush(
                new UserModelEntity(
                        input.getId().toString(),
                        input.getName(),
                        input.getLastname(),
                        input.getMail(),
                        input.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")),
                        input.getAge(),
                        input.getPhoneNumber(),
                        input.getMoreInformations(),
                        input.getPassword()
                )
        );
    }
}
