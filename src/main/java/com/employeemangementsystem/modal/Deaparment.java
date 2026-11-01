package com.employeemangementsystem.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "department")
public class Deaparment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int did;

    @Column(name = "Deaparment_Id")
    private String deptId;

    @Column(name = "department_name", nullable = false, unique = true, length = 100)
    private String departmentName;

    @Column(name = "department_code", nullable = false, unique = true, length = 20)
    private String departmentCode;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "location", length = 100)
    private String location;

    @Column(name = "status", nullable = false)
    private boolean status = true;
    @Column(name = "Deaparment_EMail",nullable = false)
    private String depEmail;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Employee> employees = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "Register_Date")
    private LocalDateTime createdAt;
}
