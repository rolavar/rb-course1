package com.riyadbank.course.app.services.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.riyadbank.course.app.model.Account;
import com.riyadbank.course.app.repository.account.IAccountRepository;
import com.riyadbank.course.app.services.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService {

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	@Qualifier("stubAccountRepository")
	private IAccountRepository repository;

	@Override
	public List<Account> findAll() {
		return repository.findAll();
	}

	@Override
	public List<Account> findAllThrowException() {
		throw new RuntimeException("Throwing an exception");
	}
	
}
