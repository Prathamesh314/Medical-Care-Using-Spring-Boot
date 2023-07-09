package com.medicare.ProjectforMedical.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "t_doctor")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank
    @Size(min = 3,max = 12,message = "Name should be of 3-12 characters")
    private String name;
    @Email
    private String email;
    private String password;
    private String image;
    private String speciality;
    private long experience;
    @OneToMany(mappedBy = "doctor")
    private Set<User> userList;
    @OneToMany(mappedBy = "doctor")
    List<Appointment> appointments;
}
