package com.medicare.ProjectforMedical.Service;

import com.medicare.ProjectforMedical.Dto.UserRequest;
import com.medicare.ProjectforMedical.Dto.UserResponse;
import com.medicare.ProjectforMedical.Model.User;
import com.medicare.ProjectforMedical.Repository.UserRepository;
import com.medicare.ProjectforMedical.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void createUser(UserRequest userRequest){
        User user = User.builder()
                .age(userRequest.getAge())
                .name(userRequest.getName())
                .address(userRequest.getAddress())
                .build();

        userRepository.save(user);
    }

    public List<UserResponse> getAllUsers(){
        return userRepository.findAll().stream().map(this::MapToResponse).toList();
    }

    public UserResponse getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","ID",id));
        return MapToResponse(user);
    }

    public void updateUser(UserRequest userRequest,Integer id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","ID",id));
        user.setName(userRequest.getName());
        user.setAge(userRequest.getAge());
        user.setAddress(userRequest.getAddress());
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
                .age(user.getAge())
                .build();
    }

}
