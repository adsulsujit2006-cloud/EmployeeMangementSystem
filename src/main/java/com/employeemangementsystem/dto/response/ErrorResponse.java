package com.employeemangementsystem.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class ErrorResponse {
    private LocalDateTime timestmp;

    private int status;

    private String error;

    private String message;

    private String path;
    private Map<String, String> validationerror;

}
