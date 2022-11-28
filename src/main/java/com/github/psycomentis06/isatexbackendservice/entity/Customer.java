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
    String city;
    String address;
    String country;
}
