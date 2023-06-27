package com.medicare.ProjectforMedical.Repository;

import com.medicare.ProjectforMedical.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

}
