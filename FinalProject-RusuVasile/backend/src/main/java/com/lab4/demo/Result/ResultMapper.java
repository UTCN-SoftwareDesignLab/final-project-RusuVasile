package com.lab4.demo.Result;
import com.lab4.demo.Result.model.Result;
import com.lab4.demo.Result.model.dto.ResultDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ResultMapper {
    @Mappings({
            @Mapping(target = "contest",expression = "java(result.getContest().getId())"),
            @Mapping(target = "team", expression = "java(result.getTeam().getId())")
    })

    ResultDTO toDto(Result result);

}
