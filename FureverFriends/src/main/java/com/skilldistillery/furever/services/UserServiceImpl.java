package com.skilldistillery.furever.services;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.furever.entities.Account;
import com.skilldistillery.furever.entities.Address;
import com.skilldistillery.furever.entities.Image;
import com.skilldistillery.furever.entities.Shelter;
import com.skilldistillery.furever.entities.Skill;
import com.skilldistillery.furever.entities.User;
import com.skilldistillery.furever.repositories.AccountRepository;
import com.skilldistillery.furever.repositories.AddressRepository;
import com.skilldistillery.furever.repositories.ImageRepository;
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
	@Autowired
	ImageRepository imgRepo;
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public List<User> displayAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User showUser(int uid, Principal principal) {
		Optional<User> ou = userRepo.findById(uid);
		if (ou.isPresent()) {
			if (ou.get().getAccount().getUsername().equals(principal.getName())) {
				return ou.get();
			} else {
				User loggedUser = userRepo.findByAccountUsernameLike(principal.getName());
				if (loggedUser.getAccount().getRole().equals("admin")) {
					if (principal.getName().equals(loggedUser.getAccount().getUsername())) {
						return ou.get();
					}
				}
			}
		}
		return null;
	}

	@Override
	public User createNewUser(User newUser) {
		try {
			System.out.println("service method: " + newUser);
			String encrypted = encoder.encode(newUser.getAccount().getPassword());
			newUser.getAccount().setPassword(encrypted);
			newUser.getAccount().setActive(true);
			newUser.getAccount().setRole("user");
			System.out.println("service method before save and flush" + newUser);
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
	public Account adminUpdateUser(User updateUser, Integer uid) {

		Account userToUpdate = null;
		Optional<Account> opt = acctRepo.findById(updateUser.getAccount().getId());
		if(opt.isPresent()) {
			userToUpdate.setActive(updateUser.getAccount().isActive());
			acctRepo.saveAndFlush(userToUpdate);
		}
		return userToUpdate;
	}

	
	@Override
	public User updateUser(User updateUser, Integer uid, Principal principal) {
		User origUser = showUser(uid, principal);
			if (origUser != null) {
				String encrypted = encoder.encode(updateUser.getAccount().getPassword());

				if (updateUser.getAccount().getUsername() != null && updateUser.getAccount().getUsername() != "") {
					Optional<Account> ua = acctRepo.findById(origUser.getAccount().getId());
					if (ua.isPresent()) {
						Account uAcct = ua.get();
						uAcct.setUsername(updateUser.getAccount().getUsername());
						uAcct.setActive(updateUser.getAccount().isActive());
//						uAcct.setPassword(updateUser.getAccount().getPassword());
//						uAcct.setActive(updateUser.getAccount().isActive());
						acctRepo.saveAndFlush(uAcct);
					}
				}
				if (updateUser.getAccount().getPassword() != null && updateUser.getAccount().getPassword() != "") {
					Optional<Account> ua = acctRepo.findById(origUser.getAccount().getId());
					if (ua.isPresent()) {
						Account uAcct = ua.get();
						updateUser.getAccount().setPassword(encrypted);
						uAcct.setPassword(updateUser.getAccount().getPassword());
//						uAcct.setPassword(encrypted);
						uAcct.setActive(updateUser.getAccount().isActive());
						acctRepo.saveAndFlush(uAcct);
					}
				}
				if (updateUser.getFname() != null && updateUser.getFname() != "") {
					origUser.setFname(updateUser.getFname());
					System.err.println("line 101 " + origUser);
				}
				if (updateUser.getLname() != null && updateUser.getLname() != "") {
					origUser.setLname(updateUser.getLname());
				}
				if (updateUser.getAge() != null) {
					origUser.setAge(updateUser.getAge());
				}
				if (updateUser.getEmail() != null) {
					origUser.setEmail(updateUser.getEmail());
				}
				
				if (updateUser.getImages() != null) {
					if (origUser.getImages().size() != 0) {
						Optional<Image> im = imgRepo.findById(origUser.getImages().get(0).getId());
						if (im.isPresent()) {
							Image image = im.get();
							image.setId(updateUser.getImages().get(0).getId());
							image.setImageUrl(updateUser.getImages().get(0).getImageUrl());
							imgRepo.saveAndFlush(image);
						} 
					} else {
						Image image = new Image();
						image.setId(1);
						image.setImageUrl(updateUser.getImages().get(0).getImageUrl());
						imgRepo.saveAndFlush(image);
						origUser.getImages().add(image);
					}
				}

				if (updateUser.getSkills() != null) {
					origUser.setSkills(updateUser.getSkills());
				}
				if (updateUser.getPhone() != null && updateUser.getPhone() != "") {
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
						if (updatedAddr.getZip() != null) {
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

	@Override
	public List<User> getVolunteersBySkill(Integer sid) {

		return userRepo.findBySkillsId(sid);

	}
}
