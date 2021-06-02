package com.lab4.demo;

import com.lab4.demo.diploma.DiplomaService;
import com.lab4.demo.team.TeamService;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        DiplomaService diplomaService=new DiplomaService();
        diplomaService.createFile("Transilvanian Black Demons","A very long textA very long textA very long textA very long textA very long textA very long textA very long textA very long textA very long textA very long textA very long textA very long textA very long text");
    }
}
