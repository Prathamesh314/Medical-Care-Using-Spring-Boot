package com.medicare.ProjectforMedical.Service;

import com.medicare.ProjectforMedical.Dto.DoctorResponse;
import com.medicare.ProjectforMedical.Dto.UserRequest;
import com.medicare.ProjectforMedical.Dto.UserResponse;
import com.medicare.ProjectforMedical.Model.Doctor;
import com.medicare.ProjectforMedical.Model.Medicine;
import com.medicare.ProjectforMedical.Model.User;
import com.medicare.ProjectforMedical.Repository.DoctorRepository;
import com.medicare.ProjectforMedical.Repository.MedicineRepository;
import com.medicare.ProjectforMedical.Repository.UserRepository;
import com.medicare.ProjectforMedical.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.id.IntegralDataTypeHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;

    public void addUser(UserRequest userRequest){
        User user = User.builder()
                .name(userRequest.getName())
                .address(userRequest.getAddress())
                .email(userRequest.getEmail())
                .age(userRequest.getAge())
                .build();
        userRepository.save(user);
    }
    

    public UserResponse findByEmail(String email){
        User user = userRepository.findByEmail(email);
        return MapToResponse(user);
    }

    public List<UserResponse> getAllUsers(){
        return userRepository.findAll().stream().map(this::MapToResponse).toList();
    }

    public UserResponse getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","ID",id));
        return MapToResponse(user);
    }

    public List<UserResponse> getUserByDoc(Integer docID){
        Doctor doctor = doctorRepository.findById(docID).orElseThrow(()-> new ResourceNotFoundException("Doctor","ID",docID));
        List<User> users = userRepository.findByDoctor(doctor);
        return users.stream().map(this::MapToResponse).toList();
    }

    public void updateUser(UserRequest userRequest,Integer id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","ID",id));
        user.setName(userRequest.getName());
        user.setAge(userRequest.getAge());
        user.setAddress(userRequest.getAddress());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        userRepository.save(user);
    }

    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }

    private UserResponse MapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .address(user.getAddress())
                .email(user.getEmail())
                .age(user.getAge())
                .build();
    }


}
