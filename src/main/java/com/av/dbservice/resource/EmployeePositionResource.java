package com.av.dbservice.resource;

import com.av.dbservice.dto.EmployeePositionDto;
import com.av.dbservice.service.EmployeePositionService;
import com.av.dbservice.util.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;


@Slf4j
@RestController
@RequestMapping("/rest/employee_position")
public class EmployeePositionResource {

    @Autowired
    private EmployeePositionService service;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getByEmployeeId(@PathVariable("id") final Long id) {
        return service.getByEmployeeId(id);
    }

    @PostMapping(value = "/assignPosition", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity assignPosition(@RequestBody EmployeePositionDto dto) {
        return service.assignPosition(dto);
    }


}
