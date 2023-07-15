package com.medicare.ProjectforMedical.Controller;

import com.medicare.ProjectforMedical.Dto.DoctorRequest;
import com.medicare.ProjectforMedical.Dto.JwtRequest;
import com.medicare.ProjectforMedical.Dto.JwtResponse;
import com.medicare.ProjectforMedical.Dto.UserRequest;
import com.medicare.ProjectforMedical.Service.CustomUserDetailsService;
import com.medicare.ProjectforMedical.Service.DoctorService;
import com.medicare.ProjectforMedical.Service.UserService;
import com.medicare.ProjectforMedical.security.JwtHelper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CustomUserDetailsService userDetailsService;
    private final AuthenticationManager manager;

    private final UserService userService;
    private final DoctorService doctorService;

    private final JwtHelper helper;

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    // add user
    @PostMapping("/user/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String addUser(@RequestBody UserRequest userRequest){
        userService.addUser(userRequest);
        return "User added successfully";
    }


    // add doctor
    @PostMapping("/doctor/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String createDoctor(@RequestBody DoctorRequest doctorRequest){
        doctorService.createDoc(doctorRequest);
        return "Doctor is created";
    }


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            this.manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

}
