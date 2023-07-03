package com.medicare.ProjectforMedical.Service;

import com.medicare.ProjectforMedical.Dto.AppointmentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendMail(String subject, String toEmail, String userName,String reason1){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        String senderName = userName;
        String reason  = reason1;
        String body = "Hiii My name is " + senderName + " and I want to set an appointment with you\nReason :- "+ reason;
        mailMessage.setFrom("kurvep79@gmail.com");
        mailMessage.setText(body);
        mailMessage.setSubject(subject);
        mailMessage.setTo(toEmail);
        mailSender.send(mailMessage);
        System.out.println("Email has sent successfully");
    }

}
