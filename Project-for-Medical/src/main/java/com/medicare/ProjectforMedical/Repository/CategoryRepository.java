package com.medicare.ProjectforMedical.Repository;

import com.medicare.ProjectforMedical.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

}
