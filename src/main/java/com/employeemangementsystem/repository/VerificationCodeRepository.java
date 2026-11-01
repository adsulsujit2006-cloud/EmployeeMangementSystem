package com.employeemangementsystem.repository;

import com.employeemangementsystem.modal.Employee;
import com.employeemangementsystem.modal.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode,Integer>{
    VerificationCode findByEmail(String email);
}
