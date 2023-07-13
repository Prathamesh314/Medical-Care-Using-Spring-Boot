package com.medicare.ProjectforMedical.config;

import com.medicare.ProjectforMedical.security.AuthenticationFilter;
import com.medicare.ProjectforMedical.security.JWTAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTAuthenticationEntryPoint entryPoint;

    private final AuthenticationFilter filter;

}
