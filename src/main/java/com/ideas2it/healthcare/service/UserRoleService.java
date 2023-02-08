package com.ideas2it.healthcare.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.github.fge.jsonpatch.JsonPatch;
import com.ideas2it.healthcare.dto.UserRoleDTO;


public interface UserRoleService {

	public List<UserRoleDTO> getUsers();
	
	
	  public Page<UserRoleDTO> getUsersPage(int page, int size);
	  
	  public UserRoleDTO getUser(String userId);
	  
	  public UserRoleDTO createUser(UserRoleDTO user);
	  
     public void updateUser(UserRoleDTO user, String id);
	 
	  public void deleteUser(String id);
	  
	  public void patchUser(String userId, JsonPatch patch);
	 
	
}
