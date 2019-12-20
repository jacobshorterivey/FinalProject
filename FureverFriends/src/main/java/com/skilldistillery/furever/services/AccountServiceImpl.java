package com.skilldistillery.furever.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.furever.entities.Account;
import com.skilldistillery.furever.repositories.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accRepo;
	
	@Override
	public List<Account> displayAllAccounts() {
		return accRepo.findAll();
	}
	
	@Override
	public Account showAccount(int id) {
		Optional<Account> os = accRepo.findById(id);
		if(os.isPresent()) {
			return os.get();
		}
		return null;
	}

	@Override
	public Account createAccount(Account newAcc) {
		newAcc.setId(0);
		try {
			return accRepo.saveAndFlush(newAcc);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Account updateAccount(Account updateAcct, int id) {
//		Optional<Account> ao = accRepo.findById(id);
		Account origAcct = showAccount(id);
		if (origAcct != null) {
			origAcct.setActive(updateAcct.isActive());
			if(updateAcct.getUsername() != null) {
			origAcct.setUsername(updateAcct.getUsername());
			}
			if(updateAcct.getPassword() != null) {
			origAcct.setPassword(updateAcct.getPassword());
			}
			if(updateAcct.getRole() != null) {
			origAcct.setRole(updateAcct.getRole());
			}
			accRepo.saveAndFlush(origAcct);
		}
		return origAcct;
	}
}
