package com.lab4.demo.team;

import com.lab4.demo.TestCreationFactory;
import com.lab4.demo.team.model.Team;
import com.lab4.demo.team.model.dto.TeamDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TeamServiceTest {
    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamRepository teamRepository;

    @BeforeEach
    void setUp() {
        teamRepository.deleteAll();
    }
    @Test
    void findAll() {
        List<Team> teamList = TestCreationFactory.listOf(Team.class);
        teamRepository.saveAll(teamList);

        List<TeamDTO> all= teamService.findAll();
        Assertions.assertEquals(teamList.size(),all.size());
    }


    @Test
    void create() {
        TeamDTO teamDTO = TeamDTO.builder()
                .name(TestCreationFactory.randomString())
                .musherName(TestCreationFactory.randomString())
                .numberOfDogs(TestCreationFactory.randomBoundedInt(10))
                .build();

        teamService.create(teamDTO);
        List<TeamDTO> all= teamService.findAll();
        Assertions.assertEquals(all.size(),1);

    }

    @Test
    void edit(){
        TeamDTO teamDTO = TeamDTO.builder()
                .name("Name before edit")
                .musherName(TestCreationFactory.randomString())
                .numberOfDogs(TestCreationFactory.randomBoundedInt(10))
                .build();
        teamService.create(teamDTO);
        Assertions.assertEquals(teamService.findById(1L).getName(),"Name before edit");
        TeamDTO teamDTO2 = TeamDTO.builder()
                .name("Name after edit")
                .musherName(TestCreationFactory.randomString())
                .numberOfDogs(TestCreationFactory.randomBoundedInt(10))
                .build();
        teamService.edit(1L, teamDTO2);

        Assertions.assertEquals(teamService.findById(1L).getName(),"Name after edit");
    }
    /*
    @Test
    void winsMedal(){
        SledDTO sledDTO=SledDTO.builder()
                .name(TestCreationFactory.randomString())
                .musherName(TestCreationFactory.randomString())
                .numberOfDogs(TestCreationFactory.randomBoundedInt(10))
                .build();
        teamService.create(sledDTO);
        Assertions.assertEquals(teamService.findById(1L).getBronzeMedals(),0);
        Assertions.assertEquals(teamService.findById(1L).getSilverMedals(),0);
        Assertions.assertEquals(teamService.findById(1L).getGoldMedals(),0);
        teamService.winsMedal(1L,2);
        teamService.winsMedal(1L,2);
        teamService.winsMedal(1L,1);
        teamService.winsMedal(1L,1);
        teamService.winsMedal(1L,1);
        teamService.winsMedal(1L,3);

        Assertions.assertEquals(teamService.findById(1L).getBronzeMedals(),1);
        Assertions.assertEquals(teamService.findById(1L).getSilverMedals(),2);
        Assertions.assertEquals(teamService.findById(1L).getGoldMedals(),3);

    }


     */
    @Test
    void delete(){
        TeamDTO teamDTO = TeamDTO.builder()
                .name(TestCreationFactory.randomString())
                .musherName(TestCreationFactory.randomString())
                .numberOfDogs(TestCreationFactory.randomBoundedInt(10))
                .build();

        teamService.create(teamDTO);
        List<TeamDTO> all= teamService.findAll();
        Assertions.assertEquals(all.size(),1);
        teamService.delete(1L);
        all= teamService.findAll();
        Assertions.assertEquals(all.size(),0);

    }


}