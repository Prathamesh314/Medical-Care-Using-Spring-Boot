package com.medicare.ProjectforMedical.Service;

import com.medicare.ProjectforMedical.Dto.MedicineRequest;
import com.medicare.ProjectforMedical.Dto.MedicineResponse;
import com.medicare.ProjectforMedical.Dto.UserResponse;
import com.medicare.ProjectforMedical.Model.Medicine;
import com.medicare.ProjectforMedical.Model.User;
import com.medicare.ProjectforMedical.Repository.MedicineRepository;
import com.medicare.ProjectforMedical.Repository.UserRepository;
import com.medicare.ProjectforMedical.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.cert.CertPathBuilder;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MedicineService {

    private final MedicineRepository medicineRepository;
    private final UserRepository userRepository;

    public void createMedicine(MedicineRequest medicineRequest,int userID){
        User user = userRepository.findById(userID).orElseThrow(()->new ResourceNotFoundException("User","ID",userID));
        Medicine medicine = Medicine.builder()
                .name(medicineRequest.getName())
                .image(medicineRequest.getImage())
                .category(medicineRequest.getCategory())
                .user(user)
                .numOfMeds(medicineRequest.getNumOfMeds())
                .build();
        medicineRepository.save(medicine);
        log.info("Medicine is saved");
    }

    public List<MedicineResponse> getAllMeds(){
        return medicineRepository.findAll().stream().map(this::MapToResponse).toList();
    }

    private MedicineResponse MapToResponse(Medicine medicine) {
        return MedicineResponse.builder()
                .name(medicine.getName())
                .image(medicine.getImage())
                .numOfMeds(medicine.getNumOfMeds())
                .category(medicine.getCategory())
                .user(MapToUserResponse(medicine.getUser()))
                .build();
    }

    private UserResponse MapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .address(user.getAddress())
                .age(user.getAge())
                .build();
    }

}
