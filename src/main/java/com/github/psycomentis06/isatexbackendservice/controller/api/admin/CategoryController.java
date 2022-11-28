package com.github.psycomentis06.isatexbackendservice.controller.api.admin;

import com.github.psycomentis06.isatexbackendservice.entity.ProductCategory;
import com.github.psycomentis06.isatexbackendservice.repository.ProductCategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final ProductCategoryRepository categoryRepository;

    public CategoryController(ProductCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createCategory(
            @RequestBody ProductCategory category
    ) {
        categoryRepository.save(category);
        Map<String, String> resp = new HashMap<>();
        resp.put("message", "Category created successfully");
        resp.put("code", String.valueOf(HttpStatus.OK.value()));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> getCategory(
            @PathVariable int id
    ) {
        Optional<ProductCategory> categoryOptional = categoryRepository.findById(id);
        categoryOptional.orElseThrow(() -> {
            throw new EntityNotFoundException("Category #" + id + " not found");
        });

        return new ResponseEntity<>(categoryOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ProductCategory>> getAll(
            @RequestParam(name = "s", required = false, defaultValue = "10") int size,
            @RequestParam(name = "p", required = false, defaultValue = "0") int page,
            @RequestParam(name = "q", required = false, defaultValue = "") String query

    ) {
        query = "% " + query + " %";
        Pageable pageable = Pageable.ofSize(size);
        return new ResponseEntity<>(categoryRepository.findProductCategoriesByNameLikeIgnoreCase(query, pageable.withPage(page), ProductCategory.class), HttpStatus.OK);
    }
}
