package com.riyadbank.course.app.services;

import java.util.List;

import com.riyadbank.course.app.model.Account;

public interface IAccountService {
	
	public List<Account> findAll();
	public List<Account> findAllThrowException();
	public Long createAccount(Account newAccount);
	public Account findById(Long id);
	public void update(Account account);
}
