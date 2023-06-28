package com.medicare.ProjectforMedical.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String image;
    private int numOfMeds;
    @OneToOne
    @MapsId
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private Category category;
    @ManyToOne
    private User user;
}
