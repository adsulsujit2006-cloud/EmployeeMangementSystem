package com.employeemangementsystem.services;

import com.employeemangementsystem.dto.request.DeparmentRequest;
import com.employeemangementsystem.dto.response.DeparmentResponse;
import com.employeemangementsystem.dto.response.EmployeeResponse;

import java.util.List;

public interface DeparmentServices {

    public DeparmentRequest registorDeaparment(DeparmentRequest deparmentRequest);
    public List<DeparmentResponse> getAllDep();
    public List<EmployeeResponse> getEmpInDept(int id);
}
