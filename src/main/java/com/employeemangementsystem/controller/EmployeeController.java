package com.employeemangementsystem.controller;

import com.employeemangementsystem.dto.request.EmployeeRequest;
import com.employeemangementsystem.dto.request.LoginOtpRequest;
import com.employeemangementsystem.dto.response.Apiresponce;
import com.employeemangementsystem.services.EmailServcie;
import com.employeemangementsystem.services.EmployeeServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeServices employeeServices;

    @Autowired
    private EmailServcie emailServcie;

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

    @PostMapping("/send/signup-otp")
    public ResponseEntity<Apiresponce> sentOtpHandler(@RequestBody LoginOtpRequest req) throws Exception {
        log.info("REST Request : send login otp to employee");
        employeeServices.sentLoginOtp(req.getEmail());

        Apiresponce res = new Apiresponce();
        res.setMessage("otp sent successfully");

        return ResponseEntity.ok(res);
    }
}