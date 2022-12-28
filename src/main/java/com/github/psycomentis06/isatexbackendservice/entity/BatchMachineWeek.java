package com.github.psycomentis06.isatexbackendservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatchMachineWeek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "WeekNumber is required")
    private int weekNumber;

    @NotNull(message = "Year is required")
    private int year;

    @NotNull(message = "Machine is required")
    @ManyToOne(targetEntity = Machine.class, optional = false)
    private Machine machine;


    @NotNull(message = "Batch is required")
    @ManyToOne(targetEntity = Batch.class, optional = false)
    private Batch batch;
}
