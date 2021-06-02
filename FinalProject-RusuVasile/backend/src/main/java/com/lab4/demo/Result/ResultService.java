package com.lab4.demo.Result;


import com.lab4.demo.Result.model.Result;
import com.lab4.demo.Result.model.dto.ResultDTO;
import com.lab4.demo.contest.ContestService;
import com.lab4.demo.contest.model.Contest;
import com.lab4.demo.team.TeamService;
import com.lab4.demo.team.model.Team;
import com.lab4.demo.team.model.dto.TeamDTO;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResultService {
    private final ResultRepository resultRepository;
    private final ResultMapper resultMapper;
    private final ContestService contestService;
    private final TeamService teamService;

    public List<ResultDTO> findAll(){
        return resultRepository.findAll()
                .stream()
                .map(resultMapper::toDto)
                .collect(Collectors.toList());
    }
    public void create (ResultDTO resultDTO){
        Contest contest=contestService.findById(resultDTO.getContest());
        Team team =teamService.findById(resultDTO.getTeam());

        if(team.getNumberOfDogs()>contest.getDogsAllowed()){
            System.out.println("Too many dogs\n");
        }
        else {
            resultRepository.save(Result.builder()
                    .contest(contest)
                    .team(team)
                    .placement(resultDTO.getPlacement())
                    .build());
        }
    }

    public boolean deservesMedal(Long teamId){
        List<Result> goodResult=resultRepository.findByPlacementAndTeam_Id(1,teamId);
        if(goodResult.size()>=3)
            return true;
        else
            return false;

    }

}
