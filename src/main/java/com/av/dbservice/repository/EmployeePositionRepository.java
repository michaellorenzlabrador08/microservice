package com.av.dbservice.repository;

import com.av.dbservice.model.Employee;
import com.av.dbservice.model.EmployeePosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeePositionRepository extends JpaRepository<EmployeePosition, Long> {

    EmployeePosition findByEmployee(Employee employee);

}
