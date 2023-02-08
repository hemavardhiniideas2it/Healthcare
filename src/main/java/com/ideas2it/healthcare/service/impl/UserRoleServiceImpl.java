package com.ideas2it.healthcare.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.ideas2it.healthcare.dto.UserRoleDTO;
import com.ideas2it.healthcare.entity.User;
import com.ideas2it.healthcare.mapper.CustomModelMapper;
import com.ideas2it.healthcare.repository.UserRepository;
import com.ideas2it.healthcare.service.UserRoleService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CustomModelMapper modelMapper;

	public List<UserRoleDTO> getUsers() {
		List<User> usersList = userRepository.findAll();
		return modelMapper.mapList(usersList, UserRoleDTO.class);
	}

	@Override
	public Page<UserRoleDTO> getUsersPage(int page, int size) {

		Pageable pageable = PageRequest.of(page, size);
		List<User> usersList = userRepository.findAll(pageable).getContent();
		Page<UserRoleDTO> pageUser = new PageImpl<UserRoleDTO>(modelMapper.mapList(usersList, UserRoleDTO.class));

		return pageUser;
	}

	@Override
	public UserRoleDTO getUser(String userId) {
		Optional<User> user = userRepository.findById(userId);
		UserRoleDTO userroledto = modelMapper.map(user.get(), UserRoleDTO.class);
		return userroledto;
	}

	@Override
	public UserRoleDTO createUser(UserRoleDTO userDTO) {

		User user = userRepository.save(modelMapper.map(userDTO, User.class));
		return modelMapper.map(user, UserRoleDTO.class);
	}

	@Override
	public void updateUser(UserRoleDTO userDTO, String id) {

		User user = modelMapper.map(userDTO, User.class);
		Optional<User> userdetail = userRepository.findById(id);

		if (userdetail != null) {
			user.setCreatedAt(userdetail.get().getCreatedAt());
			userRepository.save(user);
		}

	}

	@Override
	public void deleteUser(String id) {
		userRepository.deleteById(id);

	}

	@Override
	public void patchUser(String userId, JsonPatch patch) {
		Optional<User> user = userRepository.findById(userId);
		User userPatched = applyPatchToCustomer(patch, user);
		userRepository.save(userPatched);
	}

	/*public List<UserRoleDTO> mapUserList(List<User> users) {
		return users.stream().map(user -> modelMapper.map(user, UserRoleDTO.class)).collect(Collectors.toList());
	}*/

	private User applyPatchToCustomer(JsonPatch patch, Optional<User> user)  {
		ObjectMapper objectmapper =  JsonMapper.builder().findAndAddModules().build();
		JsonNode patched;
		try {
			patched = patch.apply(objectmapper.convertValue(user.get(), JsonNode.class));
			return objectmapper.treeToValue(patched, User.class);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (JsonPatchException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
