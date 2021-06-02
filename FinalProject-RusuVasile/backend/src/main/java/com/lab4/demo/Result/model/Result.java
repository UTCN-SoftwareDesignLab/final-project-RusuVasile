package com.lab4.demo.Result.model;
import com.lab4.demo.contest.model.Contest;
import com.lab4.demo.team.model.Team;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name="contestId")
    private Contest contest;

    @ManyToOne
    @JoinColumn(name="teamId")
    private Team team;

    @Column
    private Integer placement;


}
