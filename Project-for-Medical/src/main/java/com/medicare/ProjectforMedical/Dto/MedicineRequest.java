package com.medicare.ProjectforMedical.Dto;

import com.medicare.ProjectforMedical.Model.Category;
import com.medicare.ProjectforMedical.Model.User;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicineRequest {
    private String name;
    private String image;
    private int numOfMeds;
    private String Time;
    private String medToTake;
    private long price;
}
