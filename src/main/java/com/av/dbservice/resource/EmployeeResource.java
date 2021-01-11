package com.av.dbservice.resource;

import com.av.dbservice.dto.EmployeeDto;
import com.av.dbservice.exception.EmployeeException;
import com.av.dbservice.model.Employee;
import com.av.dbservice.repository.EmployeeRepository;
import com.av.dbservice.service.EmployeeService;
import com.av.dbservice.util.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/employee")
public class EmployeeResource {


    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity list() {
        return employeeService.list();
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity get(@PathVariable("id") final Long id) {
        return employeeService.get(id);
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody EmployeeDto dto) {
        return employeeService.add(dto);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@PathVariable("id") final Long id, @RequestBody EmployeeDto dto) {
        return employeeService.update(id, dto);
    }


    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable("id") final Long id) {
        return employeeService.delete(id);
    }


}
