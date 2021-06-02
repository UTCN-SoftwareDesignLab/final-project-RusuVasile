package com.lab4.demo.Result;

import com.lab4.demo.Result.model.dto.ResultDTO;
import com.lab4.demo.TestCreationFactory;
import com.lab4.demo.contest.ContestMapper;
import com.lab4.demo.contest.ContestRepository;
import com.lab4.demo.contest.ContestService;
import com.lab4.demo.contest.model.Contest;
import com.lab4.demo.contest.model.dto.ContestDTO;
import com.lab4.demo.team.TeamRepository;
import com.lab4.demo.team.TeamService;
import com.lab4.demo.team.model.Team;
import com.lab4.demo.team.model.dto.TeamDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ResultServiceTest {
    @Autowired
    private ResultService resultService;

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ContestService contestService;

    @Autowired
    private ContestMapper contestMapper;
    @Autowired
    private ContestRepository contestRepository;


    @BeforeEach
    void setUp() {
        contestRepository.deleteAll();
        teamRepository.deleteAll();
        resultRepository.deleteAll();
    }

    @Test
    void create() {
        Contest contestDTO=Contest.builder()
                .name(TestCreationFactory.randomString())
                .date(LocalDate.now())
                .location(TestCreationFactory.randomString())
                .dogsAllowed(10)
                .build();
        contestRepository.save(contestDTO);
        Team teamDTO = Team.builder()
                .name(TestCreationFactory.randomString())
                .musherName(TestCreationFactory.randomString())
                .numberOfDogs(TestCreationFactory.randomBoundedInt(10))
                .build();
        teamRepository.save(teamDTO);

        ResultDTO resultDTO=ResultDTO.builder()
                .contest(contestDTO.getId())
                .team(teamDTO.getId())
                .placement(TestCreationFactory.randomBoundedInt(1))
                .build();
        resultService.create(resultDTO);

        List<ResultDTO> all=resultService.findAll();

        Assertions.assertEquals(1,all.size());

    }

    @Test
    void deservesMedal(){
        Contest contestDTO=Contest.builder()
                .name(TestCreationFactory.randomString())
                .date(LocalDate.now())
                .location(TestCreationFactory.randomString())
                .dogsAllowed(10)
                .build();
        Contest contestDTO1=Contest.builder()
                .name(TestCreationFactory.randomString())
                .date(LocalDate.now())
                .location(TestCreationFactory.randomString())
                .dogsAllowed(10)
                .build();
        Contest contestDTO2=Contest.builder()
                .name(TestCreationFactory.randomString())
                .date(LocalDate.now())
                .location(TestCreationFactory.randomString())
                .dogsAllowed(10)
                .build();
        contestRepository.save(contestDTO);
        contestRepository.save(contestDTO2);
        contestRepository.save(contestDTO1);
        Team teamDTO = Team.builder()
                .name(TestCreationFactory.randomString())
                .musherName(TestCreationFactory.randomString())
                .numberOfDogs(TestCreationFactory.randomBoundedInt(10))
                .build();
        teamRepository.save(teamDTO);

        ResultDTO resultDTO=ResultDTO.builder()
                .contest(contestDTO.getId())
                .team(teamDTO.getId())
                .placement(1)
                .build();
        ResultDTO resultDTO1=ResultDTO.builder()
                .contest(contestDTO1.getId())
                .team(teamDTO.getId())
                .placement(1)
                .build();
        ResultDTO resultDTO2=ResultDTO.builder()
                .contest(contestDTO1.getId())
                .team(teamDTO.getId())
                .placement(1)
                .build();
        resultService.create(resultDTO);
        resultService.create(resultDTO1);
        resultService.create(resultDTO2);
        Assertions.assertTrue(resultService.deservesMedal(teamDTO.getId()));
    }
}