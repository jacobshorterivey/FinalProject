package com.skilldistillery.furever.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.furever.services.AuthService;

@RestController
@CrossOrigin({"*", "http://localhost:4400"})
public class AuthController {

	@Autowired
	private AuthService authService;
	
//	@RequestMapping(path = "/register", method = RequestMethod.POST)
//	public Account register(@RequestBody Account acc, HttpServletResponse res) {
//	    if (acc == null) {
//	        res.setStatus(400);
//	        return null;
//	    }
//	    acc = authService.register(acc);
//	    return acc;
//	}

	@RequestMapping(path = "/authenticate", method = RequestMethod.GET)
	public Principal authenticate(Principal principal) {
	    return principal;
	}
}
