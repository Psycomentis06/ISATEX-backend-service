package com.github.psycomentis06.isatexbackendservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "firstName is empty")
    @Length(min = 3, max = 50, message = "firstName length should be greater 3 lower 50")
    @Column(length = 60)
    private String firstName;

    @NotBlank(message = "lastName is empty")
    @Length(min = 3, max = 50, message = "lastName length should be greater 3 lower 50")
    @Column(length = 60)
    private String lastName;

    @NotBlank(message = "Department is empty")
    @Length(min = 3, max = 30, message = "lastName length should be greater 3 lower 30")
    @Column(length = 40)
    private String department;
}
