package com.github.psycomentis06.isatexbackendservice.projection;

import com.github.psycomentis06.isatexbackendservice.entity.ProductCategory;

import java.util.List;

public interface SimpleProductCategory {
    String getName();
    SimpleProductCategory getParent();
    List<SimpleProductCategoryName> getCategories();
}
