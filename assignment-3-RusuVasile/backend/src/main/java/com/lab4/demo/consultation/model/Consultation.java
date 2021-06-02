package com.lab4.demo.consultation.model;

import com.lab4.demo.patient.model.Patient;
import com.lab4.demo.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;


import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user")
    private User doctor;

    @ManyToOne
    @JoinColumn(name="patient")
    private Patient patient;

    @Column
    private LocalDate date;

    @Column
    private LocalTime time;

    @Column
    private String diagnosis;




}
