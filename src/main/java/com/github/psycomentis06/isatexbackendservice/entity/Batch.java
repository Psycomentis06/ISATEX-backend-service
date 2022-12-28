package com.github.psycomentis06.isatexbackendservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(optional = false, targetEntity = Employee.class)
    private Employee manger;

    @OneToMany(targetEntity = BatchMachineWeek.class, mappedBy = "batch", orphanRemoval = true)
    private List<BatchMachineWeek> batchMachineWeeks = new ArrayList<>();
}
