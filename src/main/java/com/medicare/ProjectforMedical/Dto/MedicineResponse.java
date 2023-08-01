package com.medicare.ProjectforMedical.Dto;


import com.medicare.ProjectforMedical.Model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicineResponse {
    private int id;
    private String name;
    private String image;
    private BigDecimal numOfMeds;
    private String time;
    private String medToTake;
    private long price;
    private CategoryResponse category;
    private UserResponse user;
}
