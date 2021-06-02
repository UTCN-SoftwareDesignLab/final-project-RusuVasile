package com.lab4.demo.contest;


import com.lab4.demo.contest.model.Contest;
import com.lab4.demo.contest.model.dto.ContestDTO;
import com.lab4.demo.team.model.dto.TeamDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContestService {
    private final ContestRepository contestRepository;
    private final ContestMapper contestMapper;

    public Contest findById(Long id){
        return contestRepository.findById(id) .orElseThrow(() -> new EntityNotFoundException("Contest not found: " + id));
    }

    public List<ContestDTO> findAll(){
        return contestRepository.findAll()
                .stream()
                .map(contestMapper::toDto)
                .collect(Collectors.toList());
    }

    public ContestDTO create(ContestDTO contestDTO){

        LocalDate date = LocalDate.parse(contestDTO.getDate().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Contest contest=contestMapper.fromDto(contestDTO);
        contest.setDate(date);
        return contestMapper.toDto(contestRepository.save(contest));
    }

    public void delete(Long id){
        contestRepository.deleteById(id);
    }

    public ContestDTO edit(Long id,ContestDTO contestDTO){
        Contest actContest=findById(id);
        actContest.setDate(contestDTO.getDate());
        actContest.setName(contestDTO.getName());
        actContest.setLocation(contestDTO.getLocation());

        return contestMapper.toDto(contestRepository.save(actContest));
    }
}
