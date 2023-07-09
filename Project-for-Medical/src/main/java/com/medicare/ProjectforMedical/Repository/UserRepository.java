package com.medicare.ProjectforMedical.Repository;

import com.medicare.ProjectforMedical.Model.Doctor;
import com.medicare.ProjectforMedical.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByDoctor(Doctor doctor);
    Optional<User> findByEmail(String email);

}
