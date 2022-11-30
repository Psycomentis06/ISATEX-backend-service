package com.github.psycomentis06.isatexbackendservice.projection;

import com.github.psycomentis06.isatexbackendservice.entity.Role;

import java.util.List;

public interface SimpleUserProjection {
    int getId();
    String getFirstName();
    String getLastName();
    String getUsername();
    String getEmail();
    boolean isVerified();
    String getImage();
    List<Role> getRoles();
}
