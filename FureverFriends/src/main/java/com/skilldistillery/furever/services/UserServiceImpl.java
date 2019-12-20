package com.skilldistillery.furever.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.furever.entities.Account;
import com.skilldistillery.furever.entities.Address;
import com.skilldistillery.furever.entities.Shelter;
import com.skilldistillery.furever.entities.Skill;
import com.skilldistillery.furever.entities.User;
import com.skilldistillery.furever.repositories.AccountRepository;
import com.skilldistillery.furever.repositories.AddressRepository;
import com.skilldistillery.furever.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	// FIELDS

	@Autowired
	private UserRepository userRepo;
	@Autowired
	AddressRepository addrRepo;
	@Autowired
	AccountRepository acctRepo;

	@Override
	public List<User> displayAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User showUser(int uid) {
		Optional<User> ou = userRepo.findById(uid);
		if (ou.isPresent()) {
			return ou.get();
		}

		return null;
	}

	@Override
	public User createNewUser(User newUser) {
		try {
			acctRepo.saveAndFlush(newUser.getAccount());
			addrRepo.saveAndFlush(newUser.getAddress());
			newUser.setId(0);

			if (newUser.getShelters() == null) {
				newUser.setShelters(new ArrayList<Shelter>());
			}
			if (newUser.getSkills() == null) {
				newUser.setSkills(new ArrayList<Skill>());
			}
			return userRepo.saveAndFlush(newUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User updateUser(User updateUser, Integer uid) {
		User origUser = showUser(uid);
		if (origUser != null) {
			if (updateUser.getAccount().getUsername() != null) {
				Optional<Account> ua = acctRepo.findById(origUser.getAccount().getId());
				if(ua.isPresent()) {
					Account uAcct = ua.get();
				uAcct.setUsername(updateUser.getAccount().getUsername());
				acctRepo.saveAndFlush(uAcct);
				}
			}
			if (updateUser.getAccount().getPassword() != null) {
				Optional<Account> ua = acctRepo.findById(origUser.getAccount().getId());
				if(ua.isPresent()) {
					Account uAcct = ua.get();
				uAcct.setPassword(updateUser.getAccount().getPassword());
				acctRepo.saveAndFlush(uAcct);
				}
			}
			if (updateUser.getFname() != null) {
				origUser.setFname(updateUser.getFname());
			}
			if (updateUser.getLname() != null) {
				origUser.setLname(updateUser.getLname());
			}
			if (updateUser.getAge() != null) {
				origUser.setAge(updateUser.getAge());
			}
			if (updateUser.getPhone() != null) {
				origUser.setPhone(updateUser.getPhone());
			}
			if (updateUser.getAddress() != null) {
				Address updatedAddr = updateUser.getAddress();

				Optional<Address> oa = addrRepo.findById(origUser.getAddress().getId());

				if (oa.isPresent()) {
					Address origAddr = oa.get();
					if (updatedAddr.getStreet() != null) {
						origAddr.setStreet(updatedAddr.getStreet());
					}
					if (updatedAddr.getStreet2() != null) {
						origAddr.setStreet2(updatedAddr.getStreet2());
					}
					if (updatedAddr.getCity() != null) {
						origAddr.setCity(updatedAddr.getCity());
					}
					if (updatedAddr.getZip() != 0) {
						origAddr.setZip(updatedAddr.getZip());
					}
					if (updatedAddr.getStateAbbr() != null) {
						origAddr.setStateAbbr(updatedAddr.getStateAbbr());
					}
					addrRepo.saveAndFlush(origAddr);
				}
				userRepo.saveAndFlush(origUser);

			}
		}
		return origUser;
	}
}
