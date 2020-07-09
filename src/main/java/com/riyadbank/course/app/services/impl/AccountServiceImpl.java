package com.riyadbank.course.app.services.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.riyadbank.course.app.model.Account;
import com.riyadbank.course.app.repository.account.IAccountRepository;
import com.riyadbank.course.app.services.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService {

	private static final Logger logger = LogManager.getLogger();
	
	private IAccountRepository repository;
	
	@Autowired
	private CacheManager cacheManager;
	
	@Autowired
	@Qualifier("jdbcAccountRepository")
	public void setRepository(IAccountRepository repository) {
		this.repository = repository;
	}

	@Cacheable(cacheNames = "accounts")
	@Override
	public List<Account> findAll() {
		return repository.findAll();
	}
	
	@Cacheable(cacheNames = "accounts", key = "#id")
	@Override
	public Account findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public List<Account> findAllThrowException() {
		throw new RuntimeException("Throwing an exception");
	}

	@Override
	public Long createAccount(Account newAccount) {
		return repository.createAccount(newAccount);
	}

	@Override
	public void update(Account account) {
		cacheEvict(account.getId());
		repository.updateAccount(account);
	}
	
	public List<Account> getAccountsOrderedByDate(){
		List<Account> accounts = repository.findAll();
		accounts.sort((a1,a2) -> a2.getDate().compareTo(a2.getDate()));
		return accounts;
	}
	
	public void cacheEvict(Long id) {
		cacheManager.getCache("accounts").evict(id);
	}
}
