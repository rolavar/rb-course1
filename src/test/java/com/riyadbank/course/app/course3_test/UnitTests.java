package com.riyadbank.course.app.course3_test;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.riyadbank.course.app.model.Account;
import com.riyadbank.course.app.repository.account.IAccountRepository;
import com.riyadbank.course.app.services.impl.AccountServiceImpl;


public class UnitTests {

	private AccountServiceImpl service;
	private IAccountRepository repository = Mockito.mock(IAccountRepository.class);
	private List<Account> listAccountsMocks;
	
	@BeforeEach
	public void setUp() {
		//service = new AccountServiceImpl(repository);
		service.setRepository(repository);
		listAccountsMocks = Stream.of(new Account(1, BigDecimal.valueOf(2500), "1234 XXXX XXXX 4567", LocalDateTime.of(2015, 06, 20, 15, 30),1),
				new Account(1, BigDecimal.valueOf(2500), "1234 XXXX XXXX 4567", LocalDateTime.of(2019, 06, 20, 15, 30),1),
				new Account(1, BigDecimal.valueOf(2500), "1234 XXXX XXXX 4567", LocalDateTime.of(2020, 06, 20, 15, 30),1)
				,new Account(1, BigDecimal.valueOf(2500), "1234 XXXX XXXX 4567", LocalDateTime.of(2010, 06, 20, 15, 30),1))
				.collect(Collectors.toList());
	}
	
	@Test
	public void test1() {
		List<Account> accounts = service.findAll();
		System.out.println(accounts.size());
		accounts.forEach(System.out::println);
	}
	
	@Test
	public void test2() {
		when(repository.findAll()).thenReturn(listAccountsMocks);
		
		List<Account> accounts = service.getAccountsOrderedByDate();
		System.out.println(accounts.size());
		accounts.forEach(System.out::println);
	}
}
