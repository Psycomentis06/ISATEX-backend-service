package com.github.psycomentis06.isatexbackendservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Machine {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    @ManyToOne(targetEntity = MachineBrand.class)
    private MachineBrand brand;
}
