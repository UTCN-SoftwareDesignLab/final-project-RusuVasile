package com.lab4.demo.contest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ContestDTO {
    private Long id;
    private String location;
    private String name;
    private Integer dogsAllowed;
    private LocalDate date;
}
