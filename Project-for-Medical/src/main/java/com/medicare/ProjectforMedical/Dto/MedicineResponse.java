package com.medicare.ProjectforMedical.Dto;


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
    private String image;
    private int numOfMeds;
    private CategoryResponse category;
    private UserResponse user;
}
