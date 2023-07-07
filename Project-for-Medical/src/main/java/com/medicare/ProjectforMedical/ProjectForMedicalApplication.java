package com.medicare.ProjectforMedical;

import com.medicare.ProjectforMedical.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class ProjectForMedicalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectForMedicalApplication.class, args);
	}

}
