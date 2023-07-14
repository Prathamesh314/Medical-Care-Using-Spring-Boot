package com.medicare.ProjectforMedical.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "t_user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
