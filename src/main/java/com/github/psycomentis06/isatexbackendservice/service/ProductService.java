package com.github.psycomentis06.isatexbackendservice.service;

import com.github.psycomentis06.isatexbackendservice.entity.Product;
import com.github.psycomentis06.isatexbackendservice.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(Product product) {
       productRepository.save(product);
    }

    public <T>Optional<T> get(Class<T> tClass, int id) {
        return productRepository.findById(tClass, id);
    }

    public <T>Page<T> getAll(Class<T> tClass, String name, Pageable pageable) {
        return productRepository.findProductsByNameLike(tClass, name, pageable);
    }
}
