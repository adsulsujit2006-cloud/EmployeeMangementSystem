package com.employeemangementsystem.controller;

import com.employeemangementsystem.dto.request.EmployeeRequest;
import com.employeemangementsystem.services.EmployeeServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeServices employeeServices;

    /*
    REST Api : Register employee with required details
     */
    @PostMapping("/register")
    public ResponseEntity<EmployeeRequest> registerEmployee(
            @RequestBody EmployeeRequest employeeRequest) {

        log.info("REST Request : register employee with required details");

        EmployeeRequest employee= employeeServices.registerEmployee(employeeRequest);

        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }
}