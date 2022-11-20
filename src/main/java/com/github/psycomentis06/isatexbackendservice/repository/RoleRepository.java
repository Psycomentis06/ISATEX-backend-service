package com.github.psycomentis06.isatexbackendservice.repository;

import com.github.psycomentis06.isatexbackendservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public  interface RoleRepository extends JpaRepository<Role, String> {
}
