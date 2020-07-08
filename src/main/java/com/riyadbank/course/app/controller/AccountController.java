package com.riyadbank.course.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.riyadbank.course.app.model.Account;
import com.riyadbank.course.app.services.IAccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	private IAccountService accountService;
	
	@Autowired
	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}

	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@GetMapping("/findAll")
	public List<Account> findAll(){
		return accountService.findAll();
	}

}
