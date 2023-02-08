package com.ideas2it.healthcare;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.ideas2it.healthcare.mapper.CustomModelMapper;

/**
*
* The main method for HealthcareApplication
*
*/

@SpringBootApplication
@EnableJpaAuditing
public class HealthcareApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthcareApplication.class, args);
	}
	@Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
	

}
