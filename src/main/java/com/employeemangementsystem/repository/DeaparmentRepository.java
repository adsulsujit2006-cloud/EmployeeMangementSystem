package com.employeemangementsystem.repository;

import com.employeemangementsystem.modal.Deaparment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeaparmentRepository extends JpaRepository<Deaparment, Integer> {

    @Query(value = "SELECT id, department_name, department_code, description, location, status FROM department",
            nativeQuery = true)
    List<Deaparment> findAllDepartmentsOnly();

    boolean existsByDepartmentName(String departmentName);

    boolean existsByDepartmentCode(String departmentCode);

    boolean existsByDepEmail(String depEmail);


}

