package com.medicare.ProjectforMedical.Repository;

import com.medicare.ProjectforMedical.Model.Medicines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicines,Integer> {

}
