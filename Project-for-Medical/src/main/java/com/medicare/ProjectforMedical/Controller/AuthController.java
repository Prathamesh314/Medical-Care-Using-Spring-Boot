package com.medicare.ProjectforMedical.Controller;

import com.medicare.ProjectforMedical.Model.JwtRequest;
import com.medicare.ProjectforMedical.Model.JwtResponse;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private  UserDetailsService userDetailsService;

    private AuthenticationManager manager;

    private JwtHelper helper;

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
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
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

}
