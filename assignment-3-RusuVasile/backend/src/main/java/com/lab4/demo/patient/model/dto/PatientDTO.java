package com.lab4.demo.patient.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {
    private Long id;
    private Long idCardNumber;
    private Long CNP;
    private String name;
    private String address;
    private Date birthDate;
}
