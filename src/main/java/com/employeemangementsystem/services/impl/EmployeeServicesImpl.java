package com.employeemangementsystem.services.impl;

import com.employeemangementsystem.dto.request.EmployeeRequest;
import com.employeemangementsystem.exception.DuplicateResourceFoundException;
import com.employeemangementsystem.exception.NullPointerException;
import com.employeemangementsystem.modal.Address;
import com.employeemangementsystem.modal.Deaparment;
import com.employeemangementsystem.modal.Employee;
import com.employeemangementsystem.repository.DeaparmentRepository;
import com.employeemangementsystem.repository.EmployeeRepository;
import com.employeemangementsystem.services.EmployeeServices;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeeServicesImpl implements EmployeeServices {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private  DeaparmentRepository deaparmentRepository;
    @Autowired
    private  ModelMapper modelMapper;


    @Override

    public EmployeeRequest registerEmployee(EmployeeRequest employeeRequest) {

        /*
        Add proper validation
         */

        if(employeeRequest==null){
            log.info("check employeeRequest is null or not : {}",employeeRequest);
            throw new NullPointerException("Enter appropate data : "+employeeRequest);
        }
        if(employeeRepository.existsByEmail(employeeRequest.getEmail())){
            log.info("Check email is duplicate : {}",employeeRequest.getEmail());
            throw new DuplicateResourceFoundException("Email alerday exist : "+employeeRequest.getEmail());
        }
        if(employeeRepository.existsByMobileNumber(employeeRequest.getMobileNumber())){
            log.info("Check email is duplicate or not : {}",employeeRequest.getMobileNumber());
            throw new DuplicateResourceFoundException("Mobile number is alreday exist : "+employeeRequest.getMobileNumber());
        }
        if(employeeRepository.existsByAadhaarNumber(employeeRequest.getAadhaarNumber())){
            log.info("check adhar number is duplicate or not : {}",employeeRequest.getAadhaarNumber());
            throw new DuplicateResourceFoundException("Adhar numbar is alreday exist : "+employeeRequest.getAadhaarNumber());
        }
        if(employeeRepository.existsByPanNumber(employeeRequest.getPanNumber())){
            log.info("Check pan number is duplacte or not : {}",employeeRequest.getPanNumber());
            throw new DuplicateResourceFoundException("Pan numbar is alreday exists :{}"+employeeRequest.getPanNumber());
        }

        Deaparment department = deaparmentRepository.findById(employeeRequest.getDid())
                .orElseThrow(() -> new DuplicateResourceFoundException(
                        "Department not found with id: " + employeeRequest.getDid()));

        /*
        Map the request DTO to the Employee entity
         */
        Employee employee = modelMapper.map(employeeRequest, Employee.class);

        /*
        Map the nested address
         */
        Address address = modelMapper.map(employeeRequest.getAddressRequest(), Address.class);
        employee.setAddress(address);

    /*
    Set the department use as FK
     */
        employee.setDepartment(department);

        Employee saved = employeeRepository.save(employee);
        return employeeRequest;
    }

}
