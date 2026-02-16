package com.sm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PatchExchange;

import com.sm.dto.UserDTO;
import com.sm.entity.User;
import com.sm.service.IUserService;
import com.sm.service.UserServiceImple;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    
	@Autowired
	private IUserService userService;

    UserController(UserServiceImple userServiceImple) {
        this.userService = userServiceImple;
    }
	
	@PostMapping("/user")
	public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userdto)
	{
		String msg = userService.createUsers(userdto);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers()
	{
		List<User> listOfUsers = userService.getallUsers();
		return  new ResponseEntity<List<User>>(listOfUsers,HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id)
	{
		User user = userService.getUserById(id);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserDTO userdto)
	{
		String msg = userService.updateUser(id, userdto);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@PatchMapping("/user/{id}")
	public ResponseEntity<String> updateUserPartialData(@PathVariable Long id, @RequestBody UserDTO userdto)
	{
		String msg = userService.updateUserPartialData(id, userdto);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<String> updateUserPartialData(@PathVariable Long id)
	{
		String msg = userService.deleteUserById(id);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	

}
