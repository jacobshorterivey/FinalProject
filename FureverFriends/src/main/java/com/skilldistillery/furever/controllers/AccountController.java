package com.skilldistillery.furever.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.furever.entities.Account;
import com.skilldistillery.furever.services.AccountService;

@RestController
@RequestMapping("api/account")
public class AccountController {
	
	@Autowired
	private AccountService svc;
	
	@GetMapping
	private List<Account> index() {
		return svc.displayAllAccounts();
	}	
	
	@GetMapping("{id}")
	private Account show(@PathVariable int id, HttpServletResponse response) {
		Account a = svc.showShelter(id);
		if (a == null) {
			response.setStatus(404);
		}
		return a;
	}
	
	@PostMapping
	public Account create(@RequestBody Account a, HttpServletResponse response) {
		Account a2 = svc.createShelter(a);
		if (a2 == null) {
			response.setStatus(500);
		}
		return a2;
	}
	
	@PutMapping("{id}")
	public Account update(@PathVariable int id, @RequestBody Account a, HttpServletResponse response) {
		Account a2 = svc.updateAccount(a, id);
		if (a2 == null) {
			response.setStatus(404);
		}
		return a2;
	}
}
