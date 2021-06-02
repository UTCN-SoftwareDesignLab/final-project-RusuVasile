package com.lab4.demo.diploma;

import com.lab4.demo.Result.ResultService;
import com.lab4.demo.team.TeamService;
import com.lab4.demo.team.model.dto.TeamDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


import static com.lab4.demo.UrlMapping.FILE;

@RestController
@RequestMapping(FILE)
@RequiredArgsConstructor
public class DiplomaController {
    private final DiplomaService diplomaService;
    private final ResultService resultService;
    @PostMapping
    public void getFile(@RequestBody TeamDTO teamDTO, HttpServletResponse response) throws IOException {
        Long teamId=teamDTO.getId();
        if(resultService.deservesMedal(teamId)) {
            diplomaService.export(teamDTO.getName(), teamDTO.getDescription());
            try {
                InputStream is = new FileInputStream("pdfFile.pdf");
                org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
                response.flushBuffer();
                is.close();
            } catch (IOException ex) {
                throw new RuntimeException("IOError writing file to output stream");
            }
        }

    }
}
