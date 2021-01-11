package com.av.dbservice.service;

import com.av.dbservice.dto.EmployeeDto;
import com.av.dbservice.exception.EmployeeException;
import com.av.dbservice.model.Employee;
import com.av.dbservice.repository.EmployeeRepository;
import com.av.dbservice.util.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public ResponseEntity list() {
        try {
            List<Employee> employees = employeeRepository.findAll();
            List<EmployeeDto> employeeDtos = employees.stream()
                    .map(x -> new EmployeeDto(x.getId(), x.getFirstName(), x.getLastName(), x.getAge()))
                    .collect(Collectors.toList());

            if (CollectionUtils.isEmpty(employeeDtos)) {
                log.error("No employees");
                throw new EmployeeException("No employees");
            } else {
                return new ResponseEntity<>(employeeDtos, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity get(Long id) {
        try {
            Employee employee = employeeRepository.findById(id).orElse(null);
            EmployeeDto employeeDto = null;
            if (employee != null) {
                employeeDto = new EmployeeDto(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getAge());
                return new ResponseEntity<>(employeeDto, HttpStatus.OK);
            } else {
                throw new EmployeeException("Employee not found");
            }

        } catch (Exception e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity add(EmployeeDto dto) {
        try {
            Employee employee = new Employee(dto.getFirstName(), dto.getLastName(), dto.getAge());
            return new ResponseEntity<>(employeeRepository.save(employee), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity update(Long id, EmployeeDto dto) {
        try {
            Employee emp = employeeRepository.findById(id).orElse(null);
            if (emp != null) {
                emp.setFirstName(dto.getFirstName());
                emp.setLastName(dto.getLastName());
                emp.setAge(dto.getAge());
                return new ResponseEntity<>(employeeRepository.save(emp), HttpStatus.OK);
            } else {
                throw new EmployeeException("Employee not found");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity delete(Long id){
        try {
            Employee emp = employeeRepository.findById(id).orElse(null);
            if (emp != null) {
                employeeRepository.delete(emp);
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                throw new EmployeeException("Employee not found");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
