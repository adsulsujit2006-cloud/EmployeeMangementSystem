package com.employeemangementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employeemangementsystem.modal.Employee;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    boolean existsByEmail(String email);
    boolean existsByMobileNumber(String mobileNumber);
    boolean existsByAadhaarNumber(String aadhaarNumber);
    boolean existsByPanNumber(String panNumber);

    Employee findByEmail(String email);

}

/*
 private String firstName;S
    private String middleName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String password;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String aadhaarNumber;
    private String panNumber;
    private Double salary;
 */