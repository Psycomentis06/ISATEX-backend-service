package com.github.psycomentis06.isatexbackendservice.repository;

import com.github.psycomentis06.isatexbackendservice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    <T> Optional<T> findUserByUsernameOrEmail(Class<T> projectionType, String username, String Email);

    <T> Optional<T> findByUsername(Class<T> projectionType, String username);

    <T> Optional<T> findByEmail(Class<T> projectionType, String Email);

    <T> Page<T> findUsersByUsernameLikeOrEmailLikeOrFirstNameLikeOrLastNameLike(Class<T> classType, String username, String email, String firstName, String lastName, Pageable pageable);

}
