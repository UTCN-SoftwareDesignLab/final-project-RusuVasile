package com.lab4.demo.consultation;

import com.lab4.demo.consultation.model.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    Boolean existsByDateAndTimeAndDoctor_id(LocalDate date, LocalTime localTime, Long doctor_id);
    Boolean existsByDateAndTimeAndPatient_id(LocalDate date, LocalTime localTime, Long patient_id);

    List<String> existsByPatient(Long patientId);
}
