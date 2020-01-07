package com.skilldistillery.furever.services;

import java.security.Principal;
import java.util.List;

import com.skilldistillery.furever.entities.Account;
import com.skilldistillery.furever.entities.User;

public interface UserService {
	
	public List<User> displayAllUsers();
	public User showUser(int uid, Principal principal);
	public User createNewUser(User newUser);
	public User updateUser(User origUser, Integer uid, Principal principal);
	public List<User> getVolunteersBySkill(Integer sid);
	public Account adminUpdateUser(User updateUser, Integer uid);
}
