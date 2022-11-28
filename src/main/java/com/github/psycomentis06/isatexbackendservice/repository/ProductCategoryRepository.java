package com.github.psycomentis06.isatexbackendservice.repository;

import com.github.psycomentis06.isatexbackendservice.entity.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    <T> Page<T> findProductCategoriesByNameLikeIgnoreCase(String name, Pageable pageable, Class<T> classType);

    <T>Optional<T> findById(Class<T> classType, int id);
}
