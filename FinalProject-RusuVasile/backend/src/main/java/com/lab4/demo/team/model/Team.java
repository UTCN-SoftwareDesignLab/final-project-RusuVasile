package com.lab4.demo.team.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 512, nullable = false)
    private String name;

    @Column(length = 512, nullable = false)
    private String musherName;

    @Column(length = 1024, nullable = false)
    private Integer numberOfDogs;

    @Column(length = 1024)
    private String description;


}

