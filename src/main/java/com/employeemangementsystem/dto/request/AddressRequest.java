package com.employeemangementsystem.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {

    private String addressLine1;

    private String addressLine2;
    @NotBlank
    private String landmark;

   @NotBlank
    private String city;

   @NotBlank
    private String district;

    @NotBlank
    private String state;

    @NotBlank
    private String country;

    @NotBlank
    private String pinCode;
}
