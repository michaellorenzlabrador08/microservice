package com.av.dbservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotNull
    @NotBlank
    private String firstName;

    @Column
    @NotNull
    @NotBlank
    private String lastName;

    @Column
    private int age;

    @OneToOne(cascade = CascadeType.ALL,
            mappedBy = "employee")
    @PrimaryKeyJoinColumn
    private EmployeePosition employeePosition;


    public Employee(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}
