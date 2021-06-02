package com.lab4.demo.Result;


import com.lab4.demo.Result.model.dto.ResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lab4.demo.UrlMapping.RESULT;

@RestController
@RequestMapping(RESULT)
@RequiredArgsConstructor
public class ResultController {

    public final ResultService resultService;

    @GetMapping
    public List<ResultDTO> allResult(){
        return resultService.findAll();
    }

    @PostMapping
    public void create(@RequestBody ResultDTO resultDTO){
        resultService.create(resultDTO);
    }
}
