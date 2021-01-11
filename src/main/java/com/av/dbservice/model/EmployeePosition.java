package com.av.dbservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class EmployeePosition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Double salary;

    @Column
    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    private Position position;

    @OneToOne
    @MapsId
    @JoinColumn(name = "employee_id")
    private Employee employee;


    public EmployeePosition(Position position, Employee employee, Double salary, boolean isActive) {
        this.position = position;
        this.employee = employee;
        this.salary = salary;
        this.isActive = isActive;
    }
}
