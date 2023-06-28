package com.medicare.ProjectforMedical.Dto;

import com.medicare.ProjectforMedical.Model.Doctor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private int age;
    private String name;
    private String address;
}
