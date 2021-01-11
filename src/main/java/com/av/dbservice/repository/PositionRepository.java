package com.av.dbservice.repository;

import com.av.dbservice.model.Employee;
import com.av.dbservice.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    //Position findByEmployee(Employee employee);
}
