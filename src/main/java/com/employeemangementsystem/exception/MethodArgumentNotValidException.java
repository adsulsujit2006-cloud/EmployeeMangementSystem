package com.employeemangementsystem.exception;

import org.springframework.validation.Errors;

public class MethodArgumentNotValidException extends RuntimeException{
    public MethodArgumentNotValidException(String msg){
        super(msg);
    }


}
