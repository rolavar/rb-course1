package com.riyadbank.course.app.accounts;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

import com.riyadbank.course.app.config.CourseAppTestConfig;
import com.riyadbank.course.app.model.Account;
import com.riyadbank.course.app.repository.account.IAccountRepository;

//TODO-05: Import the Correct config to run the test properly (CourseAppTestconfig)
@SpringBootTest
@ContextConfiguration(classes = CourseAppTestConfig.class)
public class AccountTest {
	
	//TODO-06: Find a way to inject the Account Repository in the accountRepository variable and then run the test, should it pass? Try it!!
	//TODO-07: Find a way to solve the bean qualifying and use the StubAccountRepository bean
	@Autowired
	@Qualifier("stubAccountRepository")
	private IAccountRepository accountRepository;
	
	@Test
	public void listAccounts() {
		List<Account> accounts = accountRepository.findAll();
		System.out.println(accounts.size());
		Assert.notNull(accounts, "Shouldn't be null");
		Assert.notEmpty(accounts, "Shouldn't be empty");
		
	}
	
	/*TODO-08: If you are in this step,  last test should be passed, now please uncomment the code below and make a properly implementation
	 *  due to this:
	 * a) Using Java8 Lambdas transform the List<Account> accounts to a Map with a using a properly key.
	 * b) Using Java8 Lambdas sort the List using "date" field in descending order (latest first).
	 * c) Using Java8 Lambdas pick the account with the highest balance in the list and return it as a single object (Account).
	 */
	
	@Test
	public void todo07() {
		List<Account> accounts = accountRepository.findAll();
		Map<Long, List<Account>> mapAccounts = null;
		
		// Makes an implementation to transform the accounts into a map.
		mapAccounts = accounts.stream().collect(Collectors.groupingBy(Account::getId));
		 
		Assert.notNull(mapAccounts,"map shouldn't be null");
		Assert.noNullElements(mapAccounts.values(), "map values should have elements");
		
		//Sort the account list with the requirements described in the description
		
		accounts.sort((a1,a2)-> a2.getDate().compareTo(a1.getDate()));
		assertEquals("DateTime doesn't match with the expected", 
				LocalDateTime.of(2020, 07, 03, 12, 33).toString(), accounts.get(0).getDate().toString());
		
		
		//Pick the Account with the highest balance in the list 
		 
		Account highestBalanceAccount = accounts.stream().max((a1,a2) -> a1.getBalance().compareTo(a2.getBalance())).get();
		assertEquals(BigDecimal.valueOf(2500), highestBalanceAccount.getBalance());
		
	}

}
