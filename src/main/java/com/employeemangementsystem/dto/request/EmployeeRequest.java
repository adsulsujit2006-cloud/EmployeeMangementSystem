package com.employeemangementsystem.dto.request;

import com.employeemangementsystem.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {
    private String firstName;
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
    private int did;
    private AddressRequest addressRequest;
}
