package com.lab4.demo.patient;

import com.lab4.demo.patient.model.dto.PatientDTO;
import com.lab4.demo.report.ReportServiceFactory;
import com.lab4.demo.report.ReportType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static com.lab4.demo.UrlMapping.PATIENTS;

@RestController
@RequestMapping(PATIENTS)
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public List<PatientDTO> allPatients() {
        return patientService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){ patientService.delete(id);}

    @PostMapping
    public PatientDTO create(@RequestBody PatientDTO item) {
        return patientService.create(item);
    }

    @PutMapping
    public PatientDTO edit(@RequestBody PatientDTO item) {
        return patientService.edit(item);
    }


}
