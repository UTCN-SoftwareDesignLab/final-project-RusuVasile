package com.lab4.demo.contest;

import com.lab4.demo.contest.model.dto.ContestDTO;
import com.lab4.demo.mail.EmailService;
import com.lab4.demo.mail.EmailUtil;
import com.lab4.demo.team.model.dto.TeamDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lab4.demo.UrlMapping.CONTEST;
import static com.lab4.demo.UrlMapping.ENTITY;

@RestController
@RequestMapping(CONTEST)
@RequiredArgsConstructor
public class ContestController {

    private final ContestService contestService;
    private final EmailService emailService;

    @GetMapping
    public List<ContestDTO> allContests(){
        return contestService.findAll();
    }

    @PostMapping
    public ContestDTO create(@RequestBody ContestDTO contestDTO) throws Exception {
        StringBuilder stringBuilder=new StringBuilder("asdas");
        emailService.sendConfirmedAccountEmail("vasile10rusu@yahoo.com",contestDTO.getName(),stringBuilder.toString());
        return contestService.create(contestDTO);
    }

    @PutMapping(ENTITY)
    public ContestDTO edit(@PathVariable Long id, @RequestBody ContestDTO contestDTO){
        return contestService.edit(id, contestDTO);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id){
        contestService.delete(id);
    }

}
