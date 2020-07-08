package com.riyadbank.course.app.aspects;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.riyadbank.course.app.config.CourseAppTestConfig;
import com.riyadbank.course.app.model.Account;
import com.riyadbank.course.app.services.IAccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CourseAppTestConfig.class)
public class AccountAspectTest {

	@Autowired
	private IAccountService service;
	
	@Test
	public void findAllTest() {
		List<Account> accounts = service.findAll();
		accounts.forEach(System.out::println);
	}
	
	@Test
	public void findThrowingException() {
		assertThrows(Exception.class, ()-> service.findAllThrowException());
	}
	
}
