package com.lab4.demo.contest;

import com.lab4.demo.contest.model.Contest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestRepository extends JpaRepository<Contest, Long> {
}
