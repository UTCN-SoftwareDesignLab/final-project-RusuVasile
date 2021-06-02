package com.lab4.demo.consultation;

import com.lab4.demo.consultation.model.dto.ConsultationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lab4.demo.UrlMapping.CONSULTATIONS;

@RestController
@RequestMapping(CONSULTATIONS)
@RequiredArgsConstructor
public class ConsultationController {

    private final ConsultationService consultationService;

    @GetMapping
    public List<ConsultationDto> listAllConsultations(){
        return consultationService.findAll();
    }

    @PostMapping
    public void create(@RequestBody ConsultationDto consultationDto){
        consultationService.create(consultationDto);
    }

    @PatchMapping
    public void reschedule(@RequestBody ConsultationDto consultationDto){
        consultationService.rescheduleConsultation(consultationDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        consultationService.deleteById(id);
    }
}
