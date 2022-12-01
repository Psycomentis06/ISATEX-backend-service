package com.github.psycomentis06.isatexbackendservice.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MachineBrand {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String name;

    @OneToMany(targetEntity = Machine.class, mappedBy = "brand")
    private Collection<Machine> machines;
}
