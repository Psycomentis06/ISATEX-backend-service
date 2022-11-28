package com.github.psycomentis06.isatexbackendservice.projection;

public interface SimpleProductProjection {
    int getId();
    String getName();
    String getComposition();
    SimpleProductCategory getCategory();
}
