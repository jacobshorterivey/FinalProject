package com.skilldistillery.furever.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

}
