package com.riyadbank.course.app.repository.account;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.riyadbank.course.app.model.Account;

@Repository
public class StubAccountRepository implements IAccountRepository{

	private Map<Long,Account> accounts = new HashMap<Long,Account>();
	
	public StubAccountRepository() {
		accounts.put(1L, new Account(1, BigDecimal.valueOf(2500), "1234 XXXX XXXX 4567", LocalDateTime.of(2020, 06, 20, 15, 30),1));
		accounts.put(2L, new Account(2, BigDecimal.valueOf(1090), "3456 XXXX XXXX 4567", LocalDateTime.of(2020, 07, 03, 12, 33),2));
		accounts.put(3L, new Account(3, BigDecimal.valueOf(1550), "5678 XXXX XXXX 4567", LocalDateTime.of(2020, 04, 05, 22, 21),3));
		accounts.put(4L, new Account(3, BigDecimal.valueOf(1550), "6789 XXXX XXXX 7654", LocalDateTime.of(2020, 01, 05, 22, 21),1));

		
	}
	@Override
	public List<Account> findAll() {
		return accounts.entrySet()
				.stream()
				.map(e -> e.getValue())
				.collect(Collectors.toList());
	}
	@Override
	public long createAccount(Account newAccount) {
		long id = Long.parseLong(UUID.randomUUID().toString());
		accounts.put(id, newAccount);
		return id;
	}
	@Override
	public void updateAccount(Account account) {
		Account accountToUpdate = accounts.get(account.getId());
		accountToUpdate.setBalance(account.getBalance());
		accountToUpdate.setCreditCardNumber(account.getCreditCardNumber());
		accountToUpdate.setDate(account.getDate());
		accounts.put(account.getId(), accountToUpdate);
	}

}
