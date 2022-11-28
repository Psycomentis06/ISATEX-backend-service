package com.github.psycomentis06.isatexbackendservice.projection;

import java.util.List;

public interface SimpleProductCategoryWithCategories extends SimpleProductCategory{
    List<SimpleProductCategoryName> getCategories();
}
