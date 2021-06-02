package com.lab4.demo.team;


import com.lab4.demo.team.model.Team;
import com.lab4.demo.team.model.dto.TeamDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    TeamDTO toDto(Team team);
    Team fromDto (TeamDTO teamDTO);
}
