package com.sm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sm.dto.UserDTO;
import com.sm.entity.User;
import com.sm.globalException.DuplicateResourceException;
import com.sm.globalException.ResourceNotFoundException;
import com.sm.repository.IOrderRepository;
import com.sm.repository.IUserRepository;

@Service
public class UserServiceImple implements IUserService {

	@Autowired
	private IUserRepository userRepo;

	@Override
	public String createUsers(UserDTO userdto) {

		userRepo.findByEmail(userdto.getEmail())
		.ifPresent(e -> {
			throw new DuplicateResourceException("Email is already present");
		});

		  User user = new User();
		  user.setName(userdto.getName());
		  user.setEmail(userdto.getEmail());
		  user.setPassword(userdto.getPassword());
		  
		  userRepo.save(user);

		return "User registered successfully with Id "+user.getId();
	}

	@Override
	public List<User> getallUsers() {
		
		List<User> listOfUsers = userRepo.findAll();
		
		if(listOfUsers.isEmpty())
		{
			throw new ResourceNotFoundException("No users found");
		}
		
		return listOfUsers;
	}

	@Override
	public User getUserById(Long userId) {
		
		return userRepo.findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
	}
	
	@Override
	public String updateUser(Long id, UserDTO userdto) {
		
		User user = userRepo.findById(id).orElseThrow(()->  new ResourceNotFoundException("No user found for id "+id));
		
		 Optional<User> userWithEmail = userRepo.findByEmail(userdto.getEmail());

		    if (userWithEmail.isPresent() && !userWithEmail.get().getId().equals(id)) {
		        throw new DuplicateResourceException("Email already in use");
		    }
		
		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setPassword(userdto.getPassword());
		
		 userRepo.save(user);
		 
		 return "user details updae successfully with id "+user.getId();
	}

	@Override
	public String updateUserPartialData(Long id, UserDTO userdto) {
		User user = userRepo.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

	    // Update name if provided
	    if (userdto.getName() != null) {
	        user.setName(userdto.getName());
	    }

	    // Update email if provided
	    if (userdto.getEmail() != null) {
	        Optional<User> existing = userRepo.findByEmail(userdto.getEmail());

	        if (existing.isPresent() && !existing.get().getId().equals(id)) {
	            throw new DuplicateResourceException("Email already exists");
	        }
	        user.setEmail(userdto.getEmail());
	    }

	    // Update password if provided
	    if (userdto.getPassword() != null) {
	        user.setPassword(userdto.getPassword());
	    }

	   userRepo.save(user);
	   return "User data updated successfully with id "+user.getId();
	}
	
	 @Override
	public String deleteUserById(Long id) {
		
		User user =  userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User is not found for id "+id));
		Long uId = user.getId();
		userRepo.delete(user);
		return  "User deleted successfully with id "+uId;
	}
	
}
