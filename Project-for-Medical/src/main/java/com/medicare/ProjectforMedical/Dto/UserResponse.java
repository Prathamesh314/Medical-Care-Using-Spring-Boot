package com.medicare.ProjectforMedical.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private int id;
    private int age;
    private String name;
    private String address;
    private String email;
    private String password;
    private DoctorResponse doctor;
//    private List<AppointmentResponse> appointments = new ArrayList<>();
}
