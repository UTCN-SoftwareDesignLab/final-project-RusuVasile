package com.lab4.demo.consultation;

import com.lab4.demo.consultation.model.Consultation;
import com.lab4.demo.consultation.model.dto.ConsultationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ConsultationMapper {

    @Mappings({
            @Mapping(target="patientName", expression = "java(consultation.getPatient().getName())"),
            @Mapping(target="doctorName", expression = "java(consultation.getDoctor().getUsername())"),
             })
   ConsultationDto toDto(Consultation consultation);
    Consultation fromDto(ConsultationDto consultationDto);
}
