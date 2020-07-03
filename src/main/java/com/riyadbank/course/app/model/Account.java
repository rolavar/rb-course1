package com.riyadbank.course.app.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Account {
	
	private long id;
	private BigDecimal balance;
	private String creditCardNumber;
	private LocalDateTime date;
	private long personId;
	
	
	public  Account(long id, BigDecimal balance, String creditCardNumber, LocalDateTime date, long personId) {
		super();
		this.id = id;
		this.balance = balance;
		this.creditCardNumber = creditCardNumber;
		this.date = date;
		this.personId=personId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public long getPersonId() {
		return personId;
	}
	public void setPersonId(long personId) {
		this.personId = personId;
	}
	
	
	
}
