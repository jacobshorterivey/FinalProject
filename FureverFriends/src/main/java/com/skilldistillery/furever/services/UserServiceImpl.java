package com.skilldistillery.furever.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.furever.entities.Shelter;
import com.skilldistillery.furever.entities.Skill;
import com.skilldistillery.furever.entities.User;
import com.skilldistillery.furever.repositories.UserRepository;

public class UserServiceImpl implements UserService {
	
	// FIELDS
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<User> displayAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User showUser(int uid) {
		Optional<User> ou = userRepo.findById(uid);
		if(ou.isPresent()) {
			return ou.get();
		}
		
		return null;
	}

	@Override
	public User createNewUser(User newUser) {
		try {
			newUser.setId(0);
			if(newUser.getShelters() == null) {
				newUser.setShelters(new ArrayList<Shelter>());
			}
			if(newUser.getSkills() == null) {
				newUser.setSkills(new ArrayList<Skill>());
			}
			return userRepo.saveAndFlush(newUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
//	private String username;
//	private String password;
//	private String fname;
//	private String lname;
//	private Integer age;
//	private String phone;
//	private boolean active;
//	private Address address;
//	private List<Shelter> shelters;
//	private List<Skill> skills;

	@Override
	public User updateUser(User origUser, Integer uid) {
		User updateUser = showUser(uid);
		if (updateUser != null) {
			
		}
		return null;
	}
	
}
