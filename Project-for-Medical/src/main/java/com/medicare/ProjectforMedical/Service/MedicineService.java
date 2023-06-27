package com.medicare.ProjectforMedical.Service;

import com.medicare.ProjectforMedical.Dto.MedicineRequest;
import com.medicare.ProjectforMedical.Dto.MedicineResponse;
import com.medicare.ProjectforMedical.Model.Medicines;
import com.medicare.ProjectforMedical.Model.User;
import com.medicare.ProjectforMedical.Repository.MedicineRepository;
import com.medicare.ProjectforMedical.Repository.UserRepository;
import com.medicare.ProjectforMedical.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicineService {

    private final MedicineRepository medicineRepository;
    private final UserRepository userRepository;

    public void createMed(MedicineRequest medicineRequest,Integer userId){
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","ID",userId));
        Medicines medicines = Medicines.builder()
                .name(medicineRequest.getName())
                .numberOfMedicines(medicineRequest.getNumberOfMedicines())
                .user(medicineRequest.getUser())
                .build();
        medicineRepository.save(medicines);
    }

    public List<MedicineResponse> getAllMeds(){
        return medicineRepository.findAll().stream().map(this::MapToResponse).toList();
    }

    private MedicineResponse MapToResponse(Medicines medicines) {
        return MedicineResponse.builder()
                .id(medicines.getId())
                .numberOfMedicines(medicines.getNumberOfMedicines())
                .user(medicines.getUser())
                .build();
    }

}
