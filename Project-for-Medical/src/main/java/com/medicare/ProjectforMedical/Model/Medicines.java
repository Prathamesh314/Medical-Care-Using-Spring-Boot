package com.medicare.ProjectforMedical.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_medicines")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Medicines {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int numberOfMedicines;
    @ManyToOne
    private User user;
}
