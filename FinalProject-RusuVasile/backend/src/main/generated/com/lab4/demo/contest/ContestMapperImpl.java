package com.lab4.demo.contest;

import com.lab4.demo.contest.model.Contest;
import com.lab4.demo.contest.model.dto.ContestDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-02T08:57:00+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
@Component
public class ContestMapperImpl implements ContestMapper {

    @Override
    public ContestDTO toDto(Contest contest) {
        if ( contest == null ) {
            return null;
        }

        ContestDTO contestDTO = new ContestDTO();

        contestDTO.setId( contest.getId() );
        contestDTO.setLocation( contest.getLocation() );
        contestDTO.setName( contest.getName() );
        contestDTO.setDogsAllowed( contest.getDogsAllowed() );
        contestDTO.setDate( contest.getDate() );

        return contestDTO;
    }

    @Override
    public Contest fromDto(ContestDTO contestDTO) {
        if ( contestDTO == null ) {
            return null;
        }

        Contest contest = new Contest();

        contest.setId( contestDTO.getId() );
        contest.setLocation( contestDTO.getLocation() );
        contest.setName( contestDTO.getName() );
        contest.setDogsAllowed( contestDTO.getDogsAllowed() );
        contest.setDate( contestDTO.getDate() );

        return contest;
    }
}
