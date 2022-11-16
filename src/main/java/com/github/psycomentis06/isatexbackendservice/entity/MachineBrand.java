package com.github.psycomentis06.isatexbackendservice.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MachineBrand {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @OneToMany(targetEntity = Machine.class)
    private Collection<Machine> machines;
}
