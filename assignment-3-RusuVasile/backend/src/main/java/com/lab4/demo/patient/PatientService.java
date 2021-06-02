package com.lab4.demo.patient;

import com.lab4.demo.patient.model.Patient;
import com.lab4.demo.patient.model.dto.PatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final ItemMapper itemMapper;

    public Patient findById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found: " + id));
    }
    public Patient findByName(String name){
        return patientRepository.findByName(name);
    }

    public List<PatientDTO> findAll() {
        return patientRepository.findAll().stream()
                .map(itemMapper::toDto)
                .collect(Collectors.toList());
    }

    public PatientDTO create(PatientDTO patient) {
        return itemMapper.toDto(patientRepository.save(
                itemMapper.fromDto(patient)
        ));
    }

    public void delete(Long id){
        patientRepository.deleteById(id);
    }

    public PatientDTO edit(PatientDTO item) {
        Patient actItem = findById(item.getId());
        actItem.setName(item.getName());
        actItem.setAddress(item.getAddress());
        actItem.setCNP(item.getCNP());
        actItem.setIdCardNumber(item.getIdCardNumber());
        actItem.setBirthDate(item.getBirthDate());
        return itemMapper.toDto(
                patientRepository.save(actItem)
        );
    }
}
