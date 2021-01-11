package com.av.dbservice.service;

import com.av.dbservice.dto.EmployeePositionDto;
import com.av.dbservice.exception.EmployeeException;
import com.av.dbservice.model.Employee;
import com.av.dbservice.model.EmployeePosition;
import com.av.dbservice.model.Position;
import com.av.dbservice.repository.EmployeePositionRepository;
import com.av.dbservice.repository.EmployeeRepository;
import com.av.dbservice.repository.PositionRepository;
import com.av.dbservice.util.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class EmployeePositionService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeePositionRepository epositionRepository;

    @Autowired
    private PositionRepository positionRepository;

    public ResponseEntity getByEmployeeId(Long id) {
        try {
            Employee e = employeeRepository.findById(id).orElse(null);
            if (e != null) {
                EmployeePosition employeePosition = epositionRepository.findByEmployee(e);
                return new ResponseEntity<>(employeePosition, HttpStatus.OK);
            } else {
                log.debug("Employee id not found");
                throw new EmployeeException("Employee id not found");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity assignPosition(EmployeePositionDto dto) {
        try {
            Employee e = employeeRepository.findById(dto.getEmployee_id()).orElse(null);
            Position p = positionRepository.findById(dto.getPosition_id()).orElse(null);

            if (e != null && p != null) {
                EmployeePosition employeePosition = new EmployeePosition(p, e, 2000.00, true);
                return new ResponseEntity<>(epositionRepository.save(employeePosition), HttpStatus.OK);
            } else {
                if (e == null) {
                    log.debug("Employee id not found");
                    throw new EmployeeException("Employee not found");
                } else {
                    log.debug("Position not found");
                    throw new EmployeeException("Position not found");
                }
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
