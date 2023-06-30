package com.medicare.ProjectforMedical.Service;

import com.medicare.ProjectforMedical.Dto.*;
import com.medicare.ProjectforMedical.Model.Category;
import com.medicare.ProjectforMedical.Model.Doctor;
import com.medicare.ProjectforMedical.Model.Medicine;
import com.medicare.ProjectforMedical.Model.User;
import com.medicare.ProjectforMedical.Repository.CategoryRepository;
import com.medicare.ProjectforMedical.Repository.MedicineRepository;
import com.medicare.ProjectforMedical.Repository.UserRepository;
import com.medicare.ProjectforMedical.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.cert.CertPathBuilder;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MedicineService {

    private final MedicineRepository medicineRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public void createMedicine(MedicineRequest medicineRequest,int userID,int categoryID){
        User user = userRepository.findById(userID).orElseThrow(()->new ResourceNotFoundException("User","ID",userID));
        Category category = categoryRepository.findById(categoryID).orElseThrow(()->new ResourceNotFoundException("Category","ID",categoryID));
        Medicine medicine = Medicine.builder()
                .name(medicineRequest.getName())
                .image(medicineRequest.getImage())
                .time(medicineRequest.getTime())
                .user(user)
                .category(category)
                .numOfMeds(BigDecimal.valueOf(medicineRequest.getNumOfMeds()))
                .build();
        medicineRepository.save(medicine);
        log.info("Medicine is saved");
    }

    public List<MedicineResponse> getAllMeds(){
        return medicineRepository.findAll().stream().map(this::MapToResponse).toList();
    }

    public MedicineResponse getMedById(int medID){
        Medicine medicine = medicineRepository.findById(medID).orElseThrow(()->new ResourceNotFoundException("Medicine","ID",medID));
        return MapToResponse(medicine);
    }

    public List<MedicineResponse> getMedByUser(int userID){
        User user = userRepository.findById(userID).orElseThrow(()->new ResourceNotFoundException("User","ID",userID));
        List<Medicine> medicines = medicineRepository.findByUser(user);
        return medicines.stream().map(this::MapToResponse).toList();
    }

    public List<MedicineResponse> getMedByCategory(int categoryID){
        Category category = categoryRepository.findById(categoryID).orElseThrow(()->new ResourceNotFoundException("Category","ID",categoryID));
        List<Medicine> medicines = medicineRepository.findByCategory(category);
        return medicines.stream().map(this::MapToResponse).toList();
    }

    public void updateMedicine(MedicineRequest medicineRequest,int medID){
        Medicine medicine = medicineRepository.findById(medID).orElseThrow(()->new ResourceNotFoundException("Medicine","ID",medID));
        medicine.setName(medicineRequest.getName());
        medicine.setNumOfMeds(BigDecimal.valueOf(medicineRequest.getNumOfMeds()));
        medicine.setImage(medicineRequest.getImage());
        medicine.setTime(medicineRequest.getTime());
        medicineRepository.save(medicine);
    }

    public void deleteById(int medID){
        medicineRepository.deleteById(medID);
    }

    private MedicineResponse MapToResponse(Medicine medicine) {
        return MedicineResponse.builder()
                .id(medicine.getId())
                .name(medicine.getName())
                .image(medicine.getImage())
                .time(medicine.getTime())
                .numOfMeds(medicine.getNumOfMeds())
                .category(MapToCatResponse(medicine.getCategory()))
                .user(MapToUserResponse(medicine.getUser()))
                .build();
    }

    private UserResponse MapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .address(user.getAddress())
                .age(user.getAge())
                .doctor(MapToDocResponse(user.getDoctor()))
                .build();
    }

    private DoctorResponse MapToDocResponse(Doctor doctor){
        return DoctorResponse.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .image(doctor.getImage())
                .speciality(doctor.getSpeciality())
                .build();
    }

    private CategoryResponse MapToCatResponse(Category category){
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

}
