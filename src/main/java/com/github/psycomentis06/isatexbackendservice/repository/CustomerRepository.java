package com.github.psycomentis06.isatexbackendservice.repository;

import com.github.psycomentis06.isatexbackendservice.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    <T> Optional<T> findById(Class<T> projectionType, int id);
    <T> Page<T> findUsersByUsernameLikeOrEmailLikeOrFirstNameLikeOrLastNameLike(Class<T> classType, String username, String email, String firstName, String lastName, Pageable pageable);
}
