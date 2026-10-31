package com.employeemangementsystem.exception;

public class DuplicateResourceFoundException extends RuntimeException{

    public DuplicateResourceFoundException(String msg){
        super(msg);
    }
}
