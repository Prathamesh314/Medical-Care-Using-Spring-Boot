package com.medicare.ProjectforMedical.Service;

import com.medicare.ProjectforMedical.Dto.AppointmentRequest;
import com.medicare.ProjectforMedical.Model.Appointment;
import com.medicare.ProjectforMedical.Model.User;
import com.medicare.ProjectforMedical.Repository.AppointmentRepository;
import com.medicare.ProjectforMedical.Repository.UserRepository;
import com.medicare.ProjectforMedical.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;

    public void createAppointment(AppointmentRequest appointmentRequest,int userID){
        User user = userRepository.findById(userID).orElseThrow(()->new ResourceNotFoundException("User","ID",userID));
        Appointment appointment = Appointment.builder()
                .time(new Date())
                .user(user)
                .reason(appointmentRequest.getReason())
                .build();
        appointmentRepository.save(appointment);
    }

}
