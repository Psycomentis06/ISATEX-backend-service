package com.github.psycomentis06.isatexbackendservice.projection;

import java.util.List;

public interface SimpleProductCategory {
    int getId();

    String getName();

    SimpleProductCategory getParent();

    List<SimpleProductCategoryName> getCategories();
}
