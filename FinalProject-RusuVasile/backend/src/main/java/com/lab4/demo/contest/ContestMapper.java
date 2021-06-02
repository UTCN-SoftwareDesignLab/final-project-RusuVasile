package com.lab4.demo.contest;
import com.lab4.demo.contest.model.Contest;
import com.lab4.demo.contest.model.dto.ContestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContestMapper {
    ContestDTO toDto(Contest contest);
    Contest fromDto(ContestDTO contestDTO);
}
