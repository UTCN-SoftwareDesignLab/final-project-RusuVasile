package com.lab4.demo.consultation;

import com.lab4.demo.consultation.model.Consultation;
import com.lab4.demo.consultation.model.dto.ConsultationDto;
import com.lab4.demo.patient.PatientService;
import com.lab4.demo.patient.model.Patient;
import com.lab4.demo.patient.model.dto.PatientDTO;
import com.lab4.demo.user.UserService;
import com.lab4.demo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultationService {
    private final ConsultationRepository repository;
    private final ConsultationMapper mapper;
    private final UserService userService;
    private final PatientService patientService;

    public List<ConsultationDto> findAll(){
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }
    public Consultation findById(Long id){
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("No consultation with id: " + id));
    }
    public void deleteById(Long id){repository.deleteById(id);}


    public String searchPreviousConsultationsByPatientId(Long id){
        StringBuilder historic=new StringBuilder();
        List<String> list=repository.existsByPatient(id);
        for (String s: list){
            historic.append(s);
            historic.append("\n");
        }
        return historic.toString();
    }
    public void create(ConsultationDto consultationDto){

        User doctor=userService.findByName(consultationDto.getDoctorName());
        Patient patient=patientService.findByName(consultationDto.getPatientName());

        LocalDate date = LocalDate.parse(consultationDto.getDate().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String timeString=consultationDto.getTime().toString();
        Integer hours=Integer.parseInt(timeString.substring(0,2));
        Integer minutes=Integer.parseInt(timeString.substring(3,5));
        LocalTime time = LocalTime.of(hours,minutes);

        if(!repository.existsByDateAndTimeAndDoctor_id(date,time, doctor.getId()) && !repository.existsByDateAndTimeAndPatient_id(date,time,patient.getId())){
            repository.save(Consultation.builder()
                    .id(consultationDto.getId())
                    .doctor(doctor)
                    .patient(patient)
                    .date(date)
                    .time(time)
                    .diagnosis(consultationDto.getDiagnosis())
                    .build());
        }

    }

    public void rescheduleConsultation(ConsultationDto consultationDto){

        String Ok="Availible";
        String doctorBusy="Doctor busy (has another consultation)";

        LocalDate date = LocalDate.parse(consultationDto.getDate().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String timeString=consultationDto.getTime().toString();
        Integer hours=Integer.parseInt(timeString.substring(0,2));
        Integer minutes=Integer.parseInt(timeString.substring(3,5));
        LocalTime time = LocalTime.of(hours,minutes);

        Consultation consultation=findById(consultationDto.getId());

        User doctor=consultation.getDoctor();
        Patient patient=consultation.getPatient();

          if(!repository.existsByDateAndTimeAndDoctor_id(date,time, doctor.getId()) && !repository.existsByDateAndTimeAndPatient_id(date,time,patient.getId())){
            consultation.setDate(date);
            consultation.setTime(time);
            consultation.setDiagnosis(consultationDto.getDiagnosis());
            repository.save(consultation);

        }
    }


}
