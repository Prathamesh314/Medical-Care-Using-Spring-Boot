package com.medicare.ProjectforMedical.Service;

import com.medicare.ProjectforMedical.Dto.DoctorRequest;
import com.medicare.ProjectforMedical.Dto.DoctorResponse;
import com.medicare.ProjectforMedical.Dto.UserResponse;
import com.medicare.ProjectforMedical.Model.Doctor;
import com.medicare.ProjectforMedical.Model.User;
import com.medicare.ProjectforMedical.Repository.DoctorRepository;
import com.medicare.ProjectforMedical.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public void createDoc(DoctorRequest doctorRequest){
        Doctor doctor = Doctor.builder()
                .name(doctorRequest.getName())
                .speciality(doctorRequest.getSpeciality())
                .image(doctorRequest.getImage())
                .experience(doctorRequest.getExperience())
                .email(doctorRequest.getEmail())
                .password(doctorRequest.getPassword())
                .build();

        doctorRepository.save(doctor);
        log.info("Doctor is saved");
    }

    public List<DoctorResponse> getAllDocs(){
        return doctorRepository.findAll().stream().map(this::MapToResponse).toList();
    }

    public DoctorResponse findDocById(Integer docID){
        Doctor doctor = doctorRepository.findById(docID).orElseThrow(()->new ResourceNotFoundException("Doctor","ID",docID));
        return MapToResponse(doctor);
    }

    public DoctorResponse findBySpeciality(String speciality){
        Doctor doctor = doctorRepository.findBySpeciality(speciality);
        return MapToResponse(doctor);
    }

    public void updateDoc(DoctorRequest doctorRequest,Integer docID){
        Doctor doctor = doctorRepository.findById(docID).orElseThrow(()->new ResourceNotFoundException("Doctor","ID",docID));
        doctor.setName(doctorRequest.getName());
        doctor.setImage(doctorRequest.getImage());
        doctor.setSpeciality(doctorRequest.getSpeciality());
        doctor.setExperience(doctorRequest.getExperience());
        doctor.setEmail(doctorRequest.getEmail());
        doctor.setPassword(doctorRequest.getPassword());
        doctorRepository.save(doctor);
    }

    public void deleteDoc(Integer docID){
        doctorRepository.deleteById(docID);
    }

    private DoctorResponse MapToResponse(Doctor doctor) {
        return DoctorResponse.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .image(doctor.getImage())
                .email(doctor.getEmail())
                .speciality(doctor.getSpeciality())
                .experience(doctor.getExperience())
                .build();
    }
}
