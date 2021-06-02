package com.lab4.demo.Result;

import com.lab4.demo.Result.model.Result;
import com.lab4.demo.Result.model.dto.ResultDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-02T08:57:00+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
@Component
public class ResultMapperImpl implements ResultMapper {

    @Override
    public ResultDTO toDto(Result result) {
        if ( result == null ) {
            return null;
        }

        ResultDTO resultDTO = new ResultDTO();

        resultDTO.setId( result.getId() );
        resultDTO.setPlacement( result.getPlacement() );

        resultDTO.setContest( result.getContest().getId() );
        resultDTO.setTeam( result.getTeam().getId() );

        return resultDTO;
    }
}
