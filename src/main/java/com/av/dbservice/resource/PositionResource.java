package com.av.dbservice.resource;

import com.av.dbservice.dto.PositionDto;
import com.av.dbservice.exception.EmployeeException;
import com.av.dbservice.model.Employee;
import com.av.dbservice.model.Position;
import com.av.dbservice.repository.EmployeeRepository;
import com.av.dbservice.repository.PositionRepository;
import com.av.dbservice.service.PositionService;
import com.av.dbservice.util.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/rest/position")
public class PositionResource {

    @Autowired
    private PositionService service;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity list() {
        return service.list();
    }


    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity get(@PathVariable("id") final Long id) {
        return service.get(id);
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity add(@RequestBody PositionDto dto) {
        return service.add(dto);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@PathVariable("id") final Long id, @RequestBody PositionDto dto) {
        return service.update(id, dto);
    }


    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable("id") final Long id) {
        return service.delete(id);
    }


}
