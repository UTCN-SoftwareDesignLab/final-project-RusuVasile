package com.lab4.demo.websocket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Notification {
    private String message;
    private String doctor;
    private String patient;

}
