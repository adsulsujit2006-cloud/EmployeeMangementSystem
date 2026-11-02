package com.employeemangementsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeparmentResponse {

    private String departmentName;
    private String departmentCode;
    private String description;
    private String depEmail;
    private String location;
    private String deptId;
    private boolean status;
}
