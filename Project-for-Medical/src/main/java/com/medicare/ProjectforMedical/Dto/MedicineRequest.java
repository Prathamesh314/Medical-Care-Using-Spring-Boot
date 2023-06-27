package com.medicare.ProjectforMedical.Dto;

import com.medicare.ProjectforMedical.Model.Category;
import com.medicare.ProjectforMedical.Model.User;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicineRequest {
    private String name;
    private String image;
    private int numOfMeds;
    private Category category;
    private User user;
}
