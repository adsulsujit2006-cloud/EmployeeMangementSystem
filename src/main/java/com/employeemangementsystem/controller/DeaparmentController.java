package com.employeemangementsystem.controller;

import com.employeemangementsystem.dto.request.DeparmentRequest;
import com.employeemangementsystem.services.DeparmentServices;
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
}
