package com.github.psycomentis06.isatexbackendservice.projection;

public interface SimpleProductCategory {
    int getId();

    String getName();

    SimpleProductCategory getParent();
}
