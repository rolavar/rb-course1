package com.riyadbank.course.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.riyadbank.course.app.model.Account;
import com.riyadbank.course.app.services.IAccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private IAccountService accountService;

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/findAll")
	public List<Account> findAll(){
		return accountService.findAll();
	}
	
	@PostMapping("/add")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Long createAccount(Account newAccount) {
		return accountService.createAccount(newAccount);
	}
	
	
	@GetMapping("/findById")
	@ResponseStatus(code = HttpStatus.OK)
	public Account findById(Long id) {
		return accountService.findById(id);
	}
	
	@PutMapping("/update")
	@ResponseStatus(code = HttpStatus.OK)
	public void update(@RequestBody Account account) {
		accountService.update(account);
	}

}
