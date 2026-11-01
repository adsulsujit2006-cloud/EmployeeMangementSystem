package com.employeemangementsystem.services.impl;

import com.employeemangementsystem.dto.request.DeparmentRequest;
import com.employeemangementsystem.exception.DuplicateResourceFoundException;
import com.employeemangementsystem.exception.NullPointerException;
import com.employeemangementsystem.modal.Deaparment;
import com.employeemangementsystem.repository.DeaparmentRepository;
import com.employeemangementsystem.services.DeparmentServices;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeparmentServicesImpl implements DeparmentServices {

    @Autowired
    private  ModelMapper modelMapper;

    @Autowired
    private DeaparmentRepository deaparmentRepository;


    @Override
    public DeparmentRequest registorDeaparment(DeparmentRequest deparmentRequest) {
        if(deparmentRequest==null){
            throw new NullPointerException("Value cannot be null"+deparmentRequest);
        }
        if(deaparmentRepository.existsByDepartmentName(deparmentRequest.getDepartmentName())){
            log.info("Check deparment name is duplicate or not : {}",deparmentRequest.getDepartmentName());
            throw new DuplicateResourceFoundException("Deparment name is alreday exist : "+deparmentRequest.getDepartmentName()+"Enter Unique name");
        }
        if(deaparmentRepository.existsByDepartmentCode(deparmentRequest.getDepartmentCode())){
            throw new DuplicateResourceFoundException("Deparment Code is alreday exist : "+deparmentRequest.getDepartmentCode()+"Enter Unique code");
        }

        if(deaparmentRepository.existsByDepEmail(deparmentRequest.getDepEmail())){
            throw new DuplicateResourceFoundException("Deparment email is alreday exist : "+deparmentRequest.getDepartmentName()+"Enter unique email");
        }

        /*
        Use modal mappar to convert dto request data in orignal modal class object
         */
        ModelMapper mapper = new ModelMapper();

        deaparmentRepository.save(mapper.map(deparmentRequest, Deaparment.class));
        log.info("Send response to controller layer ");
        return deparmentRequest;
    }
}
