package com.employeemangementsystem.services;

import com.employeemangementsystem.dto.request.EmployeeRequest;

public interface EmployeeServices {

    public EmployeeRequest registerEmployee(EmployeeRequest employeeRequest);
    public void sentLoginOtp(String email);
}
