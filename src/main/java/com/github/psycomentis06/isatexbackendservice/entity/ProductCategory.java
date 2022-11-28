package com.github.psycomentis06.isatexbackendservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne(targetEntity = ProductCategory.class)
    private ProductCategory parent;

    @OneToMany(targetEntity = ProductCategory.class, mappedBy = "parent")
    private List<ProductCategory> categories;

    @OneToMany(targetEntity = Product.class, mappedBy = "category")
    private List<Product> products;
}
