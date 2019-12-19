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

	@Override
	public User updateUser(User updateUser, Integer uid) {
		User origUser = showUser(uid);
		if (origUser != null) {
			if(updateUser.getUsername() != null) {
				origUser.setUsername(updateUser.getUsername());
			}
			if(updateUser.getPassword() != null) {
				origUser.setPassword(updateUser.getPassword());
			}
			if(updateUser.getFname() != null) {
				origUser.setFname(updateUser.getFname());
			}
			if(updateUser.getLname() != null) {
				origUser.setLname(updateUser.getLname());
			}
			if(updateUser.getAge() != null) {
				origUser.setAge(updateUser.getAge());
			}
			if(updateUser.getPhone() != null) {
				origUser.setPhone(updateUser.getPhone());
			}
			if(updateUser.getAddress() != null) {
				origUser.setAddress(updateUser.getAddress());
			}
			origUser.setActive(updateUser.isActive());
			userRepo.saveAndFlush(origUser);
			
		}
		return origUser;
	}
	
}
