package com.employeemangementsystem.controller;

import com.employeemangementsystem.dto.request.DeparmentRequest;
import com.employeemangementsystem.dto.response.DeparmentResponse;
import com.employeemangementsystem.dto.response.EmployeeResponse;
import com.employeemangementsystem.services.DeparmentServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/dep")
public class DeaparmentController {

    @Autowired
    private DeparmentServices deparmentServices;

    @PostMapping("/register")
    public ResponseEntity<DeparmentRequest> registorDeaparment(@RequestBody DeparmentRequest deparmentRequest){

        log.info("REST Request : Register deparment using particular details");
        DeparmentRequest dept= deparmentServices.registorDeaparment(deparmentRequest);
        return new ResponseEntity<>(dept, HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<DeparmentResponse>> getDepDetails(){
        List<DeparmentResponse>response = deparmentServices.getAllDep();
        return new ResponseEntity<List<DeparmentResponse>>(response,HttpStatus.OK);
    }
    @GetMapping("/empdetails/{id}")
    public List<EmployeeResponse> getEmpInDep(@PathVariable int id){

        List<EmployeeResponse> emp= deparmentServices.getEmpInDept(id);

        return new ResponseEntity<List<EmployeeResponse>>(emp,HttpStatus.OK).getBody();
    }
}
