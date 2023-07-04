package com.medicare.ProjectforMedical.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponse {
    private int id;
    private String name;
    private String email;
    private String image;
    private String speciality;
    private long experience;
}
