package com.github.psycomentis06.isatexbackendservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Machine {
    @Id
    @GeneratedValue
    private int id;

    @NotNull(message = "Name is missing")
    @Column(unique = true)
    private String name;

    @PastOrPresent(message = "Date can't be on the future")
    private Date purchaseDate;

    @ManyToOne(targetEntity = MachineBrand.class)
    private MachineBrand brand;

    @OneToMany(mappedBy = "machine", orphanRemoval = true)
    private List<BatchMachineWeek> batchMachineWeeks = new ArrayList<>();

}
