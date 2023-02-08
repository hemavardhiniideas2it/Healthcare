package com.ideas2it.healthcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.fge.jsonpatch.JsonPatch;
import com.ideas2it.healthcare.dto.ResponseDTO;
import com.ideas2it.healthcare.dto.UserRoleDTO;
import com.ideas2it.healthcare.service.UserRoleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserRoleController {

	@Autowired
	UserRoleService userRoleService;

	@GetMapping
	public ResponseDTO getUsers() {

		System.out.println("*****Inside listUser controller*****");

		List<UserRoleDTO> UserRoleDTOList = userRoleService.getUsers();
		ResponseDTO response = new ResponseDTO();

		if (!UserRoleDTOList.isEmpty())
			response.setSuccess(UserRoleDTOList, "List of users retrieved successfully");
		else
			response.setError(HttpStatus.NOT_FOUND.value(), "No Users available");

		return response;
	}

	@GetMapping("/{page}/{size}")
	public ResponseDTO getUsersPage(@PathVariable int page, @PathVariable int size) {

		System.out.println("*****Inside getAllUsersPage controller***** ::");

		Page<UserRoleDTO> pageUserRoleDTO = userRoleService.getUsersPage(page, size);
		ResponseDTO response = new ResponseDTO();

		if (!pageUserRoleDTO.isEmpty())
			response.setSuccess(pageUserRoleDTO, "List of users with pagination is retrieved successfully");
		else
			response.setError(HttpStatus.NOT_FOUND.value(), "No Users available");

		return response;
	}

	@GetMapping("/{id}")
	public ResponseDTO getUser(@PathVariable("id") String userId) {

		System.out.println("*****Inside getUserById controller*****");
		UserRoleDTO userRoleDTO = userRoleService.getUser(userId);

		ResponseDTO response = new ResponseDTO();

		if (userRoleDTO != null)
			response.setSuccess(userRoleDTO, "User with id " + userId + " is retrieved successfully");
		else
			response.setError(HttpStatus.NOT_FOUND.value(), "User not found for " + userId);

		return response;
	}

	@PostMapping
	public ResponseDTO createUser(@Valid @RequestBody UserRoleDTO user) {
		System.out.println("*****Inside createUser controller*****");
		UserRoleDTO userRoleDTO = userRoleService.createUser(user);

		ResponseDTO response = new ResponseDTO();

		if (userRoleDTO != null)
			response.setSuccess(userRoleDTO, "User is created successfully");
		else
			response.setError(HttpStatus.NOT_IMPLEMENTED.value(), "User is not created");

		return response;
	}

	@PutMapping("/{id}")
	public void updateUser(@RequestBody UserRoleDTO user, @PathVariable String id) {
		System.out.println("*****Inside updateUser controller*****");
		userRoleService.updateUser(user, id);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable String id) {
		System.out.println("*****Inside deleteUser controller*****");
		userRoleService.deleteUser(id);
	}

	@PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
	public void patchUser(@PathVariable("id") String userId, @RequestBody JsonPatch patch) {
		System.out.println("*****Inside patchUser controller*****");
		userRoleService.patchUser(userId, patch);
		
	}

}
