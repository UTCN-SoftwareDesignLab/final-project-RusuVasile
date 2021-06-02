package com.lab4.demo.consultation.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ConsultationDto {
    private Long id;
    private String doctorName;
    private String patientName;
    private LocalDate date;
    private LocalTime time;
    private String diagnosis;
}
