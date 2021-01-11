package com.av.dbservice.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeException extends Exception {

    public EmployeeException(String message) {
        super(message);
    }
}
