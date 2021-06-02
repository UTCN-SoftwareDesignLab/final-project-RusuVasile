package com.lab4.demo.contest.model;

import com.lab4.demo.Result.model.Result;
import com.lab4.demo.team.model.Team;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter

public class Contest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 512, nullable = false)
    private String location;

    @Column(length = 512, nullable = false)
    private String name;

    @Column(length = 1024, nullable = false)
    private Integer dogsAllowed;

    @Column
    private LocalDate date;


}
