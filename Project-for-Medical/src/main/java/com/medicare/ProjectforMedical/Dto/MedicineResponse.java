package com.medicare.ProjectforMedical.Dto;

import com.medicare.ProjectforMedical.Model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicineResponse {
    private int id;
    private String name;
    private int numberOfMedicines;
    private User user;
}
