package com.employeemangementsystem.services.impl;

import com.employeemangementsystem.dto.request.EmployeeRequest;
import com.employeemangementsystem.exception.DuplicateResourceFoundException;
import com.employeemangementsystem.exception.NullPointerException;
import com.employeemangementsystem.modal.Address;
import com.employeemangementsystem.modal.Deaparment;
import com.employeemangementsystem.modal.Employee;
import com.employeemangementsystem.modal.VerificationCode;
import com.employeemangementsystem.repository.DeaparmentRepository;
import com.employeemangementsystem.repository.EmployeeRepository;
import com.employeemangementsystem.repository.VerificationCodeRepository;
import com.employeemangementsystem.services.EmailServcie;
import com.employeemangementsystem.services.EmployeeServices;
import com.employeemangementsystem.utils.OtpUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class EmployeeServicesImpl implements EmployeeServices {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DeaparmentRepository deaparmentRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private VerificationCodeRepository verificationCodeRepository;
    @Autowired
    private EmailServcie emailService;

    @Override
    @Transactional
    public EmployeeRequest registerEmployee(EmployeeRequest employeeRequest) {

        /*
        Validation
         */
        if (employeeRequest == null) {
            log.info("employeeRequest is null");
            throw new NullPointerException("Enter appropriate data : employee request is null");
        }
        if (employeeRepository.existsByEmail(employeeRequest.getEmail())) {
            log.info("Duplicate email : {}", employeeRequest.getEmail());
            throw new DuplicateResourceFoundException("Email already exists : " + employeeRequest.getEmail());
        }
        if (employeeRepository.existsByMobileNumber(employeeRequest.getMobileNumber())) {
            log.info("Duplicate mobile number : {}", employeeRequest.getMobileNumber());
            throw new DuplicateResourceFoundException("Mobile number already exists : " + employeeRequest.getMobileNumber());
        }
        if (employeeRepository.existsByAadhaarNumber(employeeRequest.getAadhaarNumber())) {
            log.info("Duplicate Aadhaar number : {}", employeeRequest.getAadhaarNumber());
            throw new DuplicateResourceFoundException("Aadhaar number already exists : " + employeeRequest.getAadhaarNumber());
        }
        if (employeeRepository.existsByPanNumber(employeeRequest.getPanNumber())) {
            log.info("Duplicate PAN number : {}", employeeRequest.getPanNumber());
            throw new DuplicateResourceFoundException("PAN number already exists : " + employeeRequest.getPanNumber());
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
        if (employeeRequest.getAddressRequest() != null) {
            Address address = modelMapper.map(employeeRequest.getAddressRequest(), Address.class);
            employee.setAddress(address);
        }

        /*
        Set the department (used as FK)
         */
        employee.setDepartment(department);

        Employee saved = employeeRepository.save(employee);
        log.info("Employee registered with id : {}", saved.getId());
        return employeeRequest;
    }

    @Override
    @Transactional
    public void sentLoginOtp(String email) {

        if (email == null || email.isBlank()) {
            log.info("Email is blank or null : {}", email);
            throw new NullPointerException("Email is required : " + email);
        }

        Employee employee = employeeRepository.findByEmail(email);
        if (employee == null) {
            log.info("No employee found with email : {}", email);
            throw new NullPointerException("Employee not found with email : " + email);
        }

        VerificationCode isExist = verificationCodeRepository.findByEmail(email);
        if (isExist != null) {
            log.info("Delete otp to delete");
            verificationCodeRepository.delete(isExist);
        }

        String otp = OtpUtil.generateOtp();

        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setOtp(otp);
        verificationCode.setEmail(email);
        verificationCodeRepository.save(verificationCode);

        String subject = "Emp Man login/signup otp";
        String text = "Your login/signup otp is - " + otp;
        emailService.sendVerificationOtpEmail(email, otp, subject, text);
    }
}