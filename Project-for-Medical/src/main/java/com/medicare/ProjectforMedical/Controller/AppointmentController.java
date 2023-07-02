package com.medicare.ProjectforMedical.Controller;

import com.medicare.ProjectforMedical.Dto.AppointmentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    // post

    @PostMapping("/{userID}")
    public String createAppointment(@RequestBody AppointmentRequest appointmentRequest, @PathVariable Integer userID){
        return "Appointment created successfully";
    }

    // get

    // put

    // delete

}
