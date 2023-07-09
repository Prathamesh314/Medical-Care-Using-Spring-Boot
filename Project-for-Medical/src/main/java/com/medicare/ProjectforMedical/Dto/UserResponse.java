package com.medicare.ProjectforMedical.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private int id;
    private int age;
    private String name;
    private int mobNo;
    private String address;
    private String email;
    private String password;
    private String role;
    private DoctorResponse doctor;
}
