package com.lab4.demo.contest;

import com.lab4.demo.TestCreationFactory;
import com.lab4.demo.contest.model.Contest;
import com.lab4.demo.contest.model.dto.ContestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContestServiceTest {
    @Autowired
    private ContestService contestService;

    @Autowired
    private ContestRepository contestRepository;


    @BeforeEach
    void setUp() {
        contestRepository.deleteAll();
    }
    @Test
    void findAll() {
        List<Contest> contestList= TestCreationFactory.listOf(Contest.class);
        contestRepository.saveAll(contestList);

        List<ContestDTO> all=contestService.findAll();

        Assertions.assertEquals(contestList.size(),all.size());
    }

    @Test
    void create() {
        ContestDTO contestDTO=ContestDTO.builder()
                .name(TestCreationFactory.randomString())
                .date(LocalDate.now())
                .location(TestCreationFactory.randomString())
                .dogsAllowed(TestCreationFactory.randomBoundedInt(8))
                .build();
        contestService.create(contestDTO);
        List<ContestDTO> all=contestService.findAll();
        Assertions.assertEquals(all.size(),1);
    }

    @Test
    void delete() {
        ContestDTO contestDTO=ContestDTO.builder()
                .name(TestCreationFactory.randomString())
                .date(LocalDate.now())
                .location(TestCreationFactory.randomString())
                .dogsAllowed(TestCreationFactory.randomBoundedInt(8))
                .build();
        contestService.create(contestDTO);
        List<ContestDTO> all=contestService.findAll();
        Assertions.assertEquals(all.size(),1);
        contestService.delete(1L);
        all= contestService.findAll();
        Assertions.assertEquals(all.size(),0);
    }

    @Test
    void edit() {
        ContestDTO contestDTO=ContestDTO.builder()
                .name("Name before edit")
                .date(LocalDate.now())
                .location(TestCreationFactory.randomString())
                .dogsAllowed(TestCreationFactory.randomBoundedInt(8))
                .build();
        contestService.create(contestDTO);
        Assertions.assertEquals(contestService.findById(1L).getName(),"Name before edit");
        ContestDTO contestDTO2=ContestDTO.builder()
                .name("Name after edit")
                .date(LocalDate.now())
                .location(TestCreationFactory.randomString())
                .dogsAllowed(TestCreationFactory.randomBoundedInt(8))
                .build();
        contestService.edit(1L,contestDTO2);
        Assertions.assertEquals(contestService.findById(1L).getName(),"Name after edit");

    }
}