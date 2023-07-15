package com.medicare.ProjectforMedical.Controller;

import com.medicare.ProjectforMedical.Dto.*;
import com.medicare.ProjectforMedical.Model.Doctor;
import com.medicare.ProjectforMedical.Model.User;
import com.medicare.ProjectforMedical.Repository.UserRepository;
import com.medicare.ProjectforMedical.Service.AppointmentService;
import com.medicare.ProjectforMedical.Service.DoctorService;
import com.medicare.ProjectforMedical.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final DoctorService doctorService;
    private final UserRepository userRepository;
    private final AppointmentService appointmentService;


//    @PostMapping("/add")
//    @ResponseStatus(HttpStatus.CREATED)
//    public String addUser(@RequestBody UserRequest userRequest){
//        userService.addUser(userRequest);
//        return "User added successfully";
//    }

    @PostMapping("/{userID}/doctor/{doctorID}/appointment")
    @ResponseStatus(HttpStatus.OK)
    public String addAppointment(@RequestBody AppointmentRequest appointmentRequest,@PathVariable Integer userID,@PathVariable Integer doctorID){
        appointmentService.createAppointment(appointmentRequest,userID,doctorID);
        return "Appointment created";
    }

    private User MapToNewUser(UserResponse userResponse){
        return User.builder()
                .name(userResponse.getName())
                .address(userResponse.getAddress())
                .age(userResponse.getAge())
                .email(userResponse.getEmail())
                .build();
    }


    private User MapToUser(UserResponse userResponse) {
        return User.builder()
                .name(userResponse.getName())
                .address(userResponse.getAddress())
                .age(userResponse.getAge())
                .email(userResponse.getEmail())
                .doctor(MapToDoctor(userResponse.getDoctor()))
                .build();
    }


    private Doctor MapToDoctor(DoctorResponse doctor) {
        return Doctor.builder()
                .id(doctor.getId())
                .image(doctor.getImage())
                .name(doctor.getName())
                .speciality(doctor.getSpeciality())
                .experience(doctor.getExperience())
                .build();
    }

    // get
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userID}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUserByID(@PathVariable Integer userID){
        return userService.getUserById(userID);
    }


    @GetMapping("/appointment")
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentResponse> getAllAppointments(){
        return appointmentService.getAllAppointments();
    }

    // put

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public String updateUser(@RequestBody UserRequest userRequest,@PathVariable Integer userId){
        userService.updateUser(userRequest,userId);
        return ("User with ID" + userId + " has been Updated Successfully");
    }

    @PutMapping("/{appointmentID}")
    @ResponseStatus(HttpStatus.OK)
    public String updateAppointment(@RequestBody AppointmentRequest appointmentRequest,@PathVariable Integer appointmentID){
        appointmentService.updateAppointment(appointmentRequest,appointmentID);
        return "Appointment updated successfully";
    }

    // delete
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
        return "User has been deleted";
    }

    @DeleteMapping("/appointment/{appointmentID}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteAppointment(@PathVariable Integer appointmentID){
        appointmentService.deleteAppointment(appointmentID);
        return "Appointment deleted successfully";
    }

}
