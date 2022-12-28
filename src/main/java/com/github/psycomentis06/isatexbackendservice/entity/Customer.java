package com.github.psycomentis06.isatexbackendservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer extends User {
    private String city;
    private String address;
    private String country;
    private String enterpriseName;
    private String enterpriseAddress;
    private String enterprisePhone;
}
