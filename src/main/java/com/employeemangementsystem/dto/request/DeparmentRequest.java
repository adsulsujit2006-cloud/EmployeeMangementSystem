package com.employeemangementsystem.dto.request;

import com.employeemangementsystem.modal.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
public class DeparmentRequest {


    @NotBlank
    private String departmentName;

   @NotBlank
    private String departmentCode;


    private String description;

    @NotBlank
    @Email
    private String depEmail;

    @NotBlank
    private String location;

    private EmployeeRequest employeeRequest;


}
