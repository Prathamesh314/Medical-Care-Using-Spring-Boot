package com.medicare.ProjectforMedical.Repository;

import com.medicare.ProjectforMedical.Model.Appointment;
import com.medicare.ProjectforMedical.Model.User;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {

    List<Appointment> findByUser(User user);

}
