package com.medicare.ProjectforMedical.Dto;

import com.medicare.ProjectforMedical.Model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponse {
    private int id;
    private String reason;
    private String email;
    private UserResponse user;
    private Date date;
}
