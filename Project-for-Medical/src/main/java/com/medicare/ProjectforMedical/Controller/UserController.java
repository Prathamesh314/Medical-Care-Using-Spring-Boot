package com.medicare.ProjectforMedical.Controller;

import com.medicare.ProjectforMedical.Dto.UserRequest;
import com.medicare.ProjectforMedical.Dto.UserResponse;
import com.medicare.ProjectforMedical.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // create
    @PostMapping("/doctor/{docID}")
    @ResponseStatus(HttpStatus.CREATED)
    public String createUser(@RequestBody UserRequest userRequest,@PathVariable Integer docID){
        userService.createUser(userRequest,docID);
        return "User is created successfully";
    }

    // get
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userID}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUserByID(@PathVariable Integer userID){
        return userService.getUserById(userID);
    }

    @GetMapping("/doctor/{docID}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getUserByDoc(@PathVariable Integer docID){
        return userService.getUserByDocID(docID);
    }
    // put

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public String updateUser(@RequestBody UserRequest userRequest,@PathVariable Integer userId){
        userService.updateUser(userRequest,userId);
        return ("User with ID" + userId + " has been Updated Successfully");
    }

    // delete
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
        return "User has been deleted";
    }

}
