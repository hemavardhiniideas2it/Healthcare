package com.ideas2it.healthcare.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "role")
public class Role {
	
	@Id
	@Column(name="role_id")
	int roleId;
	String description;
	String access;
	
}
