package com.medicare.ProjectforMedical.Service;

import com.medicare.ProjectforMedical.Dto.AppointmentResponse;
import com.medicare.ProjectforMedical.Model.Doctor;
import com.medicare.ProjectforMedical.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendMail(User user, Doctor doctor, String reason1){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        String senderName = user.getName();
        String reason  = reason1;
        String body  = "Subject: Appointment Confirmation - Your Upcoming Medical Consultation\n" +
                "\n" +
                "Dear Dr. " + doctor.getName() +",\n" +
                "\n" +
                "I hope this email finds you well. I am writing to confirm my appointment with you on [Date] at [Time]. I am grateful for the opportunity to receive your expertise and guidance regarding my medical concerns.\n" +
                "\n" +
                "I appreciate your dedication to providing personalized and compassionate care. I trust that our appointment will be valuable in addressing my health needs and developing an appropriate treatment plan.\n" +
                "\n" +
                "To ensure a productive consultation, I will come prepared with any relevant medical records, test results, or medications. I believe this will assist you in gaining a comprehensive understanding of my medical history and enable us to make informed decisions together.\n" +
                "\n" +
                "I would like to confirm the location of the medical center. Please let me know if there are any specific instructions or if I need to bring any additional documentation.\n" +
                "\n" +
                "If, for any reason, I need to reschedule or cancel my appointment, I will notify you at the earliest opportunity. I understand the importance of timely communication and ensuring that other patients can receive the care they need.\n" +
                "\n" +
                "Thank you for your attention to my health matters. I look forward to meeting with you on [Date]. If there is any further information or preparation required from my end, please do not hesitate to let me know.\n" +
                "\n" +
                "Best regards,\n" +
                "\n" +
                user.getName()+"\n" +
                Integer.toString(user.getMobNo());
        mailMessage.setFrom(user.getEmail());
        mailMessage.setText(body);
        mailMessage.setSubject("Appointment");
        mailMessage.setTo(doctor.getEmail());
        mailSender.send(mailMessage);
        System.out.println("Email has sent successfully");
    }

}
