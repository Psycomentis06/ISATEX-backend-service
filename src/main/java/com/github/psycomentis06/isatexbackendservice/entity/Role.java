package com.github.psycomentis06.isatexbackendservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_CUSTOMER = "ROLE_CUSTOMER";
    public static final String ROLE_PRIVILEGED_CUSTOMER = "ROLE_PRIVILEGED_CUSTOMER";
    public static final String ROLE_SECRETARY = "ROLE_SECRETARY";
    public static final String ROLE_STOREKEEPER = "ROLE_STOREKEEPER";
    public static final String ROLE_FORWARDING_AGENT = "ROLE_FORWARDING_AGENT";
    public static final String ROLE_CUSTOMS_OFFICER = "ROLE_CUSTOMS_OFFICER";


    public Role setRole(String role) {
        if (!(this.name.startsWith("ROLE_") || this.name.startsWith("role_"))) {
            this.name = "ROLE_" + role.toUpperCase();
        }
        return this;
    }
}
