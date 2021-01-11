package com.av.dbservice.dto;

import lombok.Data;

@Data
public class EmployeePositionDto {
    private Long id;
    private Double salary;
    private boolean isActive;
    private Long position_id;
    private Long employee_id;
}
