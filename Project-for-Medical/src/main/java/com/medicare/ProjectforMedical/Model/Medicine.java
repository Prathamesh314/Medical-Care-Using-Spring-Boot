package com.medicare.ProjectforMedical.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "t_medicines")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank
    private String name;
    private String image;
    @NotBlank
    private BigDecimal numOfMeds;
    @NotBlank
    private String medToTakeAtATime;
    @NotBlank
    private long price;
    @NotBlank
    private String time;
    @OneToOne
    @MapsId
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private Category category;
    @ManyToOne
    private User user;
}
