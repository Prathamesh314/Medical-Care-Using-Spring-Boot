package com.medicare.ProjectforMedical.Repository;

import com.medicare.ProjectforMedical.Model.Category;
import com.medicare.ProjectforMedical.Model.Medicine;
import com.medicare.ProjectforMedical.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine,Integer> {
    List<Medicine> findByUser(User user);
    List<Medicine> findByCategory(Category category);
}
