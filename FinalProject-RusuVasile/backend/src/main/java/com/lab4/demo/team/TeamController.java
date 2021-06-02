package com.lab4.demo.team;

import com.lab4.demo.Result.ResultService;
import com.lab4.demo.diploma.DiplomaService;
import com.lab4.demo.team.model.dto.TeamDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.lab4.demo.UrlMapping.ENTITY;
import static com.lab4.demo.UrlMapping.TEAM;

@RestController
@RequestMapping(TEAM)
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;
    private final DiplomaService diplomaService;
    private final ResultService resultService;

    @GetMapping
    public List<TeamDTO> allTeams(){
        return teamService.findAll();
    }

    @PostMapping
    public TeamDTO create(@RequestBody TeamDTO teamDTO){
        return teamService.create(teamDTO);
    }

    @PutMapping(ENTITY)
    public TeamDTO edit(@PathVariable Long id, @RequestBody TeamDTO team){
        return teamService.edit(id, team);
    }

    @GetMapping(ENTITY)
    public TeamDTO getTeam(@PathVariable Long id){
        return teamService.get(id);
    }
    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id){
        teamService.delete(id);
    }



}
