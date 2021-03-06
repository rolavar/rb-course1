package com.riyadbank.course.app.repository.account;

import java.util.List;

import com.riyadbank.course.app.model.Account;

public interface IAccountRepository {

	public List<Account> findAll();
	public void updateAccount(Account account);
	public long createAccount(Account newAccount);
	public Account findById(Long id);
}
