package com.sm.service;

import java.util.List;

import com.sm.dto.UserDTO;
import com.sm.entity.User;

public interface IUserService {
	
	public String createUsers(UserDTO user);
	public List<User> getallUsers();
	public User getUserById(Long userId);
	public String updateUser(Long id, UserDTO userdto);
	public String updateUserPartialData(Long id, UserDTO userdto);
	public String deleteUserById(Long id);
}
