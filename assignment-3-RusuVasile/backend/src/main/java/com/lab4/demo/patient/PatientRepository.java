package com.lab4.demo.patient;

import com.lab4.demo.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("select p from Patient p where p.name =?1")
    Patient findByName(String name);

}
