package com.medicare.ProjectforMedical.Service;

import com.medicare.ProjectforMedical.Dto.AppointmentRequest;
import com.medicare.ProjectforMedical.Dto.AppointmentResponse;
import com.medicare.ProjectforMedical.Dto.DoctorResponse;
import com.medicare.ProjectforMedical.Dto.UserResponse;
import com.medicare.ProjectforMedical.Model.Appointment;
import com.medicare.ProjectforMedical.Model.Doctor;
import com.medicare.ProjectforMedical.Model.User;
import com.medicare.ProjectforMedical.Repository.AppointmentRepository;
import com.medicare.ProjectforMedical.Repository.DoctorRepository;
import com.medicare.ProjectforMedical.Repository.UserRepository;
import com.medicare.ProjectforMedical.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private  final UserRepository userRepository;
    private final DoctorRepository doctorRepository;

    private final EmailService emailService;

    // create
    public void createAppointment(AppointmentRequest appointmentRequest,int userID,int docID){
        User user = userRepository.findById(userID).orElseThrow(()->new ResourceNotFoundException("User","ID",userID));
        Doctor doctor = doctorRepository.findById(docID).orElseThrow(()->new ResourceNotFoundException("Doctor","ID",docID));
        user.setDoctor(doctor);
        userRepository.save(user);
        Appointment appointment = new Appointment();
        appointment.setDate(new Date());
        appointment.setUser(user);
        appointment.setReason(appointmentRequest.getReason());
        appointment.setEmail(appointment.getEmail());
        appointmentRepository.save(appointment);
        emailService.sendMail("Appointment",appointmentRequest.getEmail(),user.getName(),appointmentRequest.getReason());
    }

    // get

    public List<AppointmentResponse> getAllAppointments(){
        return appointmentRepository.findAll().stream().map(this::MapToAppResponse).toList();
    }

    private AppointmentResponse MapToAppResponse(Appointment appointment) {
        return AppointmentResponse.builder()
                .id(appointment.getId())
                .reason(appointment.getReason())
                .user(MapToUserResponse(appointment.getUser()))
                .email(appointment.getEmail())
                .date(appointment.getDate())
                .build();
    }

    private UserResponse MapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .address(user.getAddress())
                .doctor(MapToDocResponse(user.getDoctor()))
                .email(user.getEmail())
                .password(user.getPassword())
                .age(user.getAge())
                .build();
    }

    private DoctorResponse MapToDocResponse(Doctor doctor) {
        return DoctorResponse.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .image(doctor.getImage())
                .speciality(doctor.getSpeciality())
                .experience(doctor.getExperience())
                .build();
    }

    // update

    public void updateAppointment(AppointmentRequest appointmentRequest,int id){
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Appointment","ID",id));
        appointment.setReason(appointmentRequest.getReason());
        appointment.setEmail(appointment.getEmail());
        appointment.setDate(new Date());
        appointmentRepository.save(appointment);
    }

    // delete

    public void deleteAppointment(Integer id){
        appointmentRepository.deleteById(id);
    }

    private UserResponse MapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .address(user.getAddress())
                .email(user.getEmail())
                .password(user.getPassword())
                .age(user.getAge())
                .build();
    }
}
