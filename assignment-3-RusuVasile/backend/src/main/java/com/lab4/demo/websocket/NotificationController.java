package com.lab4.demo.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
public class NotificationController {
    @CrossOrigin(origins = "*", maxAge = 3600)
    @MessageMapping("/checkIn")
    @SendTo("/doctor/checkInNotification")
    public Notification patientCheckIn(Received consultationInfo)throws Exception{
        Thread.sleep(1000); // simulated delay
        StringBuilder message=new StringBuilder();
        message.append("Dr."+consultationInfo.getDoctor()+" ,your "+consultationInfo.getTime()+"'o clock consultation has arrived.");
        message.append("Consultation Details:\n\nPatient Name: "+consultationInfo.getPatient());
        message.append("Details: "+consultationInfo.getDescription());
        Notification toDoctor=new Notification(message.toString(),consultationInfo.getDoctor(), consultationInfo.getPatient());

        return toDoctor;
    }
}
