package com.github.psycomentis06.isatexbackendservice.repository;

import com.github.psycomentis06.isatexbackendservice.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    <T> Page<T> findByNameLikeIgnoreCase(String name, Pageable pageable, Class<T> classType);

    <T>Optional<T> findById(Class<T> classType, int id);

    <T>Page<T> findProductsByNameLike(Class<T> classType, String name,
                                      Pageable pageable);
}
