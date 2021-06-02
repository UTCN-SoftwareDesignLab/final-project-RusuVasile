package com.lab4.demo.team;

import com.lab4.demo.team.model.Team;
import com.lab4.demo.team.model.dto.TeamDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-02T08:57:00+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
@Component
public class TeamMapperImpl implements TeamMapper {

    @Override
    public TeamDTO toDto(Team team) {
        if ( team == null ) {
            return null;
        }

        TeamDTO teamDTO = new TeamDTO();

        teamDTO.setId( team.getId() );
        teamDTO.setNumberOfDogs( team.getNumberOfDogs() );
        teamDTO.setMusherName( team.getMusherName() );
        teamDTO.setName( team.getName() );
        teamDTO.setDescription( team.getDescription() );

        return teamDTO;
    }

    @Override
    public Team fromDto(TeamDTO teamDTO) {
        if ( teamDTO == null ) {
            return null;
        }

        Team team = new Team();

        team.setId( teamDTO.getId() );
        team.setName( teamDTO.getName() );
        team.setMusherName( teamDTO.getMusherName() );
        team.setNumberOfDogs( teamDTO.getNumberOfDogs() );
        team.setDescription( teamDTO.getDescription() );

        return team;
    }
}
