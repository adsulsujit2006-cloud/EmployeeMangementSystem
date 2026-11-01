package com.employeemangementsystem.modal;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class VerificationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String otp;

    private String email;

    @OneToOne
    private Employee employee;

}
