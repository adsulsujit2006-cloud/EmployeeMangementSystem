package com.employeemangementsystem.services.impl;

import com.employeemangementsystem.dto.request.DeparmentRequest;
import com.employeemangementsystem.dto.response.DeparmentResponse;
import com.employeemangementsystem.dto.response.EmployeeResponse;
import com.employeemangementsystem.exception.DuplicateResourceFoundException;
import com.employeemangementsystem.exception.NullPointerException;
import com.employeemangementsystem.exception.ResourceNotFoundException;
import com.employeemangementsystem.modal.Deaparment;
import com.employeemangementsystem.modal.Employee;
import com.employeemangementsystem.repository.DeaparmentRepository;
import com.employeemangementsystem.services.DeparmentServices;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DeparmentServicesImpl implements DeparmentServices {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DeaparmentRepository deaparmentRepository;


    @Override
    public DeparmentRequest registorDeaparment(DeparmentRequest deparmentRequest) {
        if (deparmentRequest == null) {
            throw new NullPointerException("Value cannot be null" + deparmentRequest);
        }
        if (deaparmentRepository.existsByDepartmentName(deparmentRequest.getDepartmentName())) {
            log.info("Check deparment name is duplicate or not : {}", deparmentRequest.getDepartmentName());
            throw new DuplicateResourceFoundException("Deparment name is alreday exist : " + deparmentRequest.getDepartmentName() + "Enter Unique name");
        }
        if (deaparmentRepository.existsByDepartmentCode(deparmentRequest.getDepartmentCode())) {
            throw new DuplicateResourceFoundException("Deparment Code is alreday exist : " + deparmentRequest.getDepartmentCode() + "Enter Unique code");
        }

        if (deaparmentRepository.existsByDepEmail(deparmentRequest.getDepEmail())) {
            throw new DuplicateResourceFoundException("Deparment email is alreday exist : " + deparmentRequest.getDepartmentName() + "Enter unique email");
        }

        /*
        Use modal mappar to convert dto request data in orignal modal class object
         */
        ModelMapper mapper = new ModelMapper();

        deaparmentRepository.save(mapper.map(deparmentRequest, Deaparment.class));
        log.info("Send response to controller layer ");
        return deparmentRequest;
    }

    @Override
    public List<DeparmentResponse> getAllDep() {
        List<Deaparment> dep = deaparmentRepository.findAllDepartmentsOnly();
        List<DeparmentResponse> listDep = new ArrayList<>();

        for (Deaparment d : dep) {
            DeparmentResponse response = modelMapper.map(d, DeparmentResponse.class);
            listDep.add(response);
        }
        return listDep;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeResponse> getEmpInDept(int id) {
        if(id==0){
            log.info("Id will be zeo or less than zero :{}",id);
            throw new ResourceNotFoundException("Id must be grater than zeoro : "+id);
        }
        Deaparment department = deaparmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));

        List<EmployeeResponse> responsesemp = new ArrayList<>();
        List<Employee> emp = department.getEmployees();
        if(emp.isEmpty()){
            log.info("Check employee in deparment prsent or not");
            throw new ResourceNotFoundException("Employee " +
                    "not found this particular deparment :"+department.getDepartmentName());
        }
        for (Employee employee : emp) {

            EmployeeResponse employees = modelMapper.map(employee, EmployeeResponse.class);
            responsesemp.add(employees);
        }

        return responsesemp;
    }
}
