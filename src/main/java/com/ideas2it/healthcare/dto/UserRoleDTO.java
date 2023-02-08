package com.ideas2it.healthcare.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Component
@Data
public class UserRoleDTO{

	private int id;
	@NotBlank(message = "User name cannot be blank")
	private String name;
	private String createdBy;
	private String updatedBy;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private int rowVersion;
	private RoleDTO role;
	
	

}
