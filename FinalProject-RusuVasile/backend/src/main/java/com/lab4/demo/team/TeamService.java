package com.lab4.demo.team;

import com.lab4.demo.team.model.Team;
import com.lab4.demo.team.model.dto.TeamDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    public Team findById(Long id){
        return teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sled not found: " + id));
    }

    public List<TeamDTO> findAll(){
        return teamRepository.findAll()
                .stream()
                .map(teamMapper::toDto)
                .collect(Collectors.toList());
    }

    public String generateDescription(Integer numberOfDogs,String musherName){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("Musher: "+ musherName+" and his "+numberOfDogs+" dogs: ");
        String[] dogNames={"Aegir","Alfheim","Alföðr","Arne","Asgard","Axel","Balder",
                            "Baldur","Bein","Berserk","Bjorn","Bloodaxe","Bragi","Brann",
                            "Borr","Buri","Draugr","Eric the red","Fenrir","Fitch","Foresti",
                            "Freyr","Garmr","Gandalf","Hati","Hermond","Hoenir","Heimdallr","Heimdall",
                            "Huginn","Ivar the Boneles","Ironside","Jarv","Jormungandr","Jotun","Kappi",
                            "Kraken","Leif","Lodjur","Loki","Magni","Magnus","Mani","Mare","Mjollnir",
                            "Modi","Muninn","Njord","Odin","Olaf","Orm","Raider","Ragnarok","Ratatoskr",
                            "Rollo of Normandy","Revna","Rune","Snake eye","Skoll","Sunna","Sigurdr",
                            "Sindri","Surtr","Susi","Sjør","Thor","Tyr","Ullr","Ull","Ulf","Vallhalla",
                            "Vidar","Viking","Varulv","Ymir"};
        Random r=new Random();
        for(int i=0;i<numberOfDogs;i++) {
            int random=r.nextInt(dogNames.length);
            if (i != numberOfDogs - 1)
                stringBuilder.append(dogNames[random]+", ");
            else
                stringBuilder.append(dogNames[random]+".");
        }
        return stringBuilder.toString();

    }

    public TeamDTO create(TeamDTO teamDTO){
        teamDTO.setDescription(generateDescription(teamDTO.getNumberOfDogs(), teamDTO.getMusherName()));
        Team team = teamMapper.fromDto(teamDTO);
        return teamMapper.toDto(teamRepository.save(team));
    }

    public void delete(Long id){
        teamRepository.deleteById(id);
    }


    public TeamDTO edit(Long id, TeamDTO teamDTO){
        Team actTeam =findById(id);

        actTeam.setName(teamDTO.getName());
        actTeam.setMusherName(teamDTO.getMusherName());
        actTeam.setNumberOfDogs(teamDTO.getNumberOfDogs());
        actTeam.setDescription(generateDescription(teamDTO.getNumberOfDogs(),teamDTO.getMusherName()));
        return  teamMapper.toDto(teamRepository.save(actTeam));
    }

    public TeamDTO get(Long id) {
        return teamMapper.toDto(teamRepository.findById(id).get());
    }
 }
