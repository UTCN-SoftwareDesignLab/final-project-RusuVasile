package com.lab4.demo.websocket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class Received {
    private String patient;
    private String doctor;
    private String description;
    private String date;
    private String time;

}
