package com.av.dbservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionDto{
    private Long id;

    private String jobTitle;

}
