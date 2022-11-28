package com.github.psycomentis06.isatexbackendservice.controller.api.admin;

import com.github.psycomentis06.isatexbackendservice.entity.Product;
import com.github.psycomentis06.isatexbackendservice.projection.SimpleProductProjection;
import com.github.psycomentis06.isatexbackendservice.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String , String >> createProduct(
            @RequestBody Product product
    ) {
        productService.createProduct(product);
        Map<String, String> resp = new HashMap<>();
        resp.put("message", "Product created successfully");
        resp.put("code", String.valueOf(HttpStatus.OK.value()));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SimpleProductProjection> getProduct(
            @PathVariable int id
    ) {
        Optional<SimpleProductProjection> product = productService.get(SimpleProductProjection.class, id);
        product.orElseThrow(() -> {
            throw new EntityNotFoundException("Product #" + id + " not found");
        });
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<SimpleProductProjection>> getAll(
            @RequestParam(name = "s", required = false, defaultValue = "10") int size,
            @RequestParam(name = "p", required = false, defaultValue = "0") int page,
            @RequestParam(name = "q", required = false, defaultValue = "") String query
    ) {
        query = "%" + query + "%";
        Pageable pageable = Pageable.ofSize(size);
        return new ResponseEntity<>(productService.getAll(SimpleProductProjection.class, query, pageable.withPage(page)), HttpStatus.OK);
    }
}
