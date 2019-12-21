package com.skilldistillery.furever.services;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.furever.entities.Account;
import com.skilldistillery.furever.entities.Address;
import com.skilldistillery.furever.entities.Shelter;
import com.skilldistillery.furever.repositories.AccountRepository;
import com.skilldistillery.furever.repositories.AddressRepository;
import com.skilldistillery.furever.repositories.ShelterRepository;

@Service
public class ShelterServiceImpl implements ShelterService {

	// FIELDS

	@Autowired
	private ShelterRepository shelterRepo;
	@Autowired
	private AccountRepository acctRepo;
	@Autowired
	private AddressRepository addrRepo;
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public List<Shelter> displayAllShelters() {
		return shelterRepo.findAll();
	}

	@Override
	public Shelter showShelter(int id) {
		Optional<Shelter> os = shelterRepo.findById(id);
		if (os.isPresent()) {
			return os.get();
		}
		return null;
	}

	@Override
	public Shelter createShelter(Shelter newShelter) {
		newShelter.setId(0);
		try {
			String encrypted = encoder.encode(newShelter.getAccount().getPassword());
			newShelter.getAccount().setPassword(encrypted);
			newShelter.getAccount().setActive(true);
			newShelter.getAccount().setRole("shelter");
			acctRepo.saveAndFlush(newShelter.getAccount());
			addrRepo.saveAndFlush(newShelter.getAddress());
			return shelterRepo.saveAndFlush(newShelter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Shelter updateShelter(Shelter updateShelter, int id, Principal principal) {
		Shelter orgShelter = showShelter(id);
		if (principal.getName().equals(orgShelter.getAccount().getUsername())) {
			if (orgShelter != null) {
				String encrypted = encoder.encode(updateShelter.getAccount().getPassword());

				if (updateShelter.getAccount().getUsername() != null
						&& updateShelter.getAccount().getUsername() != "") {
					Optional<Account> sa = acctRepo.findById(orgShelter.getAccount().getId());
					if (sa.isPresent()) {
						Account sAcct = sa.get();
						sAcct.setUsername(updateShelter.getAccount().getUsername());
						acctRepo.saveAndFlush(sAcct);
					}
				}
				if (updateShelter.getAccount().getPassword() != null
						&& updateShelter.getAccount().getPassword() != "") {
					Optional<Account> sa = acctRepo.findById(orgShelter.getAccount().getId());
					if (sa.isPresent()) {
						Account sAcct = sa.get();

						updateShelter.getAccount().setPassword(encrypted);
						sAcct.setPassword(updateShelter.getAccount().getPassword());
						acctRepo.saveAndFlush(sAcct);
					}
				}
				if (updateShelter.getPhone() != null && updateShelter.getPhone() != "") {
					orgShelter.setPhone(updateShelter.getPhone());
				}
				if (updateShelter.getEmail() != null && updateShelter.getEmail() != "") {
					orgShelter.setEmail(updateShelter.getEmail());
				}
				if (updateShelter.getName() != null && updateShelter.getName() != "") {
					orgShelter.setName(updateShelter.getName());
				}
				if (updateShelter.getWebsiteUrl() != null && updateShelter.getWebsiteUrl() != "") {
					orgShelter.setWebsiteUrl(updateShelter.getWebsiteUrl());
				}
				if (updateShelter.getPets() != null) {
					orgShelter.setPets(updateShelter.getPets());
				}
				if (updateShelter.getUsers() != null) {
					orgShelter.setUsers(updateShelter.getUsers());
				}
				if (updateShelter.getImages() != null) {
					orgShelter.setImages(updateShelter.getImages());
				}
				if (updateShelter.getAddress() != null) {
					Address updatedAddr = updateShelter.getAddress();

					Optional<Address> oa = addrRepo.findById(orgShelter.getAddress().getId());

					if (oa.isPresent()) {
						Address origAddr = oa.get();
						if (updatedAddr.getStreet() != null && updatedAddr.getStreet() != "") {
							origAddr.setStreet(updatedAddr.getStreet());
						}
						if (updatedAddr.getStreet2() != null && updatedAddr.getStreet2() != "") {
							origAddr.setStreet2(updatedAddr.getStreet2());
						}
						if (updatedAddr.getCity() != null && updatedAddr.getCity() != "") {
							origAddr.setCity(updatedAddr.getCity());
						}
						if (updatedAddr.getZip() != null) {
							origAddr.setZip(updatedAddr.getZip());
						}
						if (updatedAddr.getStateAbbr() != null && updatedAddr.getStateAbbr() != "") {
							origAddr.setStateAbbr(updatedAddr.getStateAbbr());
						}
						addrRepo.saveAndFlush(origAddr);
					}
				}
				shelterRepo.saveAndFlush(orgShelter);
			}
		}
		return orgShelter;
	}
}
