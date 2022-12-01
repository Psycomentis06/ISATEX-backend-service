package com.github.psycomentis06.isatexbackendservice.repository;

import com.github.psycomentis06.isatexbackendservice.entity.MachineBrand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MachineBrandRepository extends JpaRepository<MachineBrand, Integer> {
    <T>Page<T> findAllByNameLike(Class<T> tClass, Pageable pageable, String name);

    <T>Optional<T> findById(Class<T> tClass, int id);
}
