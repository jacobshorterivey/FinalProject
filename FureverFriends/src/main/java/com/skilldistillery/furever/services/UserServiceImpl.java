package com.skilldistillery.furever.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.furever.entities.Address;
import com.skilldistillery.furever.entities.Shelter;
import com.skilldistillery.furever.entities.Skill;
import com.skilldistillery.furever.entities.User;
import com.skilldistillery.furever.repositories.AddressRepository;
import com.skilldistillery.furever.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	// FIELDS
	
	@Autowired
	private UserRepository userRepo;
	@Autowired AddressRepository addrRepo;

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
			System.out.println("pre-flush" + newUser.getAddress());
			Address newUserAddr = addrRepo.saveAndFlush(newUser.getAddress());
			System.out.println("post-flush" + newUserAddr);
			newUser.setId(0);
			newUser.setAddress(newUserAddr);
			System.out.println("new User" + newUser);
			
			if(newUser.getShelters() == null) {
				newUser.setShelters(new ArrayList<Shelter>());
			}
			if(newUser.getSkills() == null) {
				newUser.setSkills(new ArrayList<Skill>());
			}
			return userRepo.saveAndFlush(newUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User updateUser(User origUser, Integer uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(User updateUser, Integer uid) {
		User origUser = showUser(uid);
		if (origUser != null) {

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
			userRepo.saveAndFlush(origUser);
			
		}
		return origUser;
	}
	
}
