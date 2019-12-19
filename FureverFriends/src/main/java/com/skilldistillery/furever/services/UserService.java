package com.skilldistillery.furever.services;

import java.util.List;

import com.skilldistillery.furever.entities.User;

public interface UserService {
	
	public List<User> displayAllUsers();
	public User showUser(int uid);
	public User createNewUser(User newUser);
	public User updateUser(User origUser, Integer uid);
	public boolean userActivationStatus(Integer uid);

}
