package com.skilldistillery.furever.services;

import java.util.List;

import com.skilldistillery.furever.entities.Account;

public interface AccountService {

	public List<Account> displayAllAccounts();

	public Account showShelter(int id);

	public Account createShelter(Account newAcc);

	public Account updateAccount(Account account, int id);

}
