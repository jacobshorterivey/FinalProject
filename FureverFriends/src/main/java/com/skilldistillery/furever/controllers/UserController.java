package com.skilldistillery.furever.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.furever.entities.User;
import com.skilldistillery.furever.services.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {
	
	@Autowired private UserService uSvc;
	
	@GetMapping
	public List<User> index(){
		return uSvc.displayAllUsers();
		
	}
	@GetMapping("{uid}")
	public User showUser(@PathVariable int uid, HttpServletResponse response){
		User user = uSvc.showUser(uid);
		if (user == null) {
			response.setStatus(404);
		}
		return user;
		
	}
	
	@PostMapping
	public User createUser(@RequestBody User newUser, HttpServletResponse response) {
		User userCreated = uSvc.createNewUser(newUser);
		if (userCreated == null) {
			response.setStatus(500);
		}
		return userCreated;
		
	}
}
