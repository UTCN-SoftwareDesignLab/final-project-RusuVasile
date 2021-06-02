package com.lab4.demo.Result;

import com.lab4.demo.Result.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result,Long> {
    List<Result> findByPlacementAndTeam_Id(Integer place,Long id);
}
