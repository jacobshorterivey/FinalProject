package com.skilldistillery.furever.services;

import java.util.List;

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
	public Account showShelter(int id) {
		return accRepo.findById(id);
	}

	@Override
	public Account createShelter(Account newAcc) {
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
	public Account updateShelter(Account account, int id) {
		Account update = accRepo.findById(id);
		if (update != null) {
			update.setActive(account.isActive());
			update.setUsername(account.getUsername());
			update.setPassword(account.getPassword());
			update.setRole(account.getRole());
			accRepo.saveAndFlush(update);
		}
		return update;
	}
}
