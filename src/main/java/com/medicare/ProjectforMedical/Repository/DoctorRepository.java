package com.medicare.ProjectforMedical.Repository;

import com.medicare.ProjectforMedical.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    Doctor findBySpeciality(String speciality);
    Optional<Doctor> findByEmail(String email);
}
