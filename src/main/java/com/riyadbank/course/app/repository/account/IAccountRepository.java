package com.riyadbank.course.app.repository.account;

import java.util.List;

import com.riyadbank.course.app.model.Account;

public interface IAccountRepository {

	public List<Account> findAll();
}
