package com.av.dbservice.service;

import com.av.dbservice.dto.PositionDto;
import com.av.dbservice.exception.EmployeeException;
import com.av.dbservice.model.Position;
import com.av.dbservice.repository.PositionRepository;
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
public class PositionService {

    @Autowired
    private PositionRepository repository;

    public ResponseEntity list() {
        try {
            log.info("get position list");
            List<Position> positions = repository.findAll();
            List<PositionDto> dtos = positions.stream().map(x -> new PositionDto(x.getId(), x.getJobTitle())).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(dtos)) {
                log.error("position list is empty");
                throw new EmployeeException("No position is available");
            }
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity get(Long id) {
        try {
            Position position = repository.findById(id).orElse(null);
            PositionDto positionDto = null;
            if (position != null) {
                positionDto = new PositionDto(position.getId(), position.getJobTitle());
                return new ResponseEntity<>(positionDto, HttpStatus.OK);
            } else {
                log.error("Position not found");
                throw new EmployeeException("Position not found");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity add(PositionDto dto) {
        try {
            Position position = new Position(dto.getJobTitle());
            return new ResponseEntity<>(repository.save(position), HttpStatus.OK);
        } catch (Exception e) {
            log.error("error adding position ");
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity update(Long id, PositionDto dto) {
        try {
            Position position = repository.findById(id).orElse(null);
            if (position != null) {
                position.setJobTitle(dto.getJobTitle());
                return new ResponseEntity<>(repository.save(position), HttpStatus.OK);
            } else {
                throw new EmployeeException("Position not found");
            }

        } catch (Exception e) {
            log.error("error update");
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity delete(Long id) {
        try {
            Position position = repository.findById(id).orElse(null);
            if (position != null) {
                repository.delete(position);
            } else {
                throw new EmployeeException("Position id not found");
            }
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            log.error("error in deleting position");
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
