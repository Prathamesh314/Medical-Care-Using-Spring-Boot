package com.medicare.ProjectforMedical.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int age;
    @NotBlank
    @Size(min = 3,max = 12,message = "Name should be of 3-12 characters")
    private String name;
    private String roles;
    private int mobNo;
    private String address;
    @Email
    private String email;
    private String password;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Medicine> medicines = new ArrayList<>();
    @ManyToOne
    private Doctor doctor;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Appointment> appointmentList = new ArrayList<>();

}
