package com.riyadbank.course.app.course3_test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.riyadbank.course.app.controller.AccountController;
import com.riyadbank.course.app.model.Account;
import com.riyadbank.course.app.services.IAccountService;

//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@WebMvcTest
public class ControllerTest {

//	@LocalServerPort
//	private int port;
//	
//	@Autowired
//	private TestRestTemplate restTemplate;
	private List<Account> listAccountsMocks;
	@BeforeEach
	public void setUp() {
		listAccountsMocks = Stream.of(new Account(1, BigDecimal.valueOf(2500), "1234 XXXX XXXX 4567", LocalDateTime.of(2015, 06, 20, 15, 30),1),
				new Account(1, BigDecimal.valueOf(2500), "1234 XXXX XXXX 4567", LocalDateTime.of(2019, 06, 20, 15, 30),1),
				new Account(1, BigDecimal.valueOf(2500), "1234 XXXX XXXX 4567", LocalDateTime.of(2020, 06, 20, 15, 30),1)
				,new Account(1, BigDecimal.valueOf(2500), "1234 XXXX XXXX 4567", LocalDateTime.of(2010, 06, 20, 15, 30),1))
				.collect(Collectors.toList());
	}
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private AccountController accountController;
	
	@Test
	public void testController() {
		assertThat(accountController).isNotNull();
	}
	
	@MockBean
	private IAccountService service;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void getTest() throws Exception {
		when(service.findAll()).thenReturn(listAccountsMocks);
		
		mockMvc.perform(get("/account/findAll"))
		.andDo(print())
		.andExpect(status().isOk());
	//	.andExpect(content().json(mapper.writeValueAsString(listAccountsMocks)));		
	}
	
	@Test
	public void postTest() throws JsonProcessingException, Exception {
		Account newAcount = new Account();
		when(service.createAccount(newAcount)).thenReturn(123L);
		
		mockMvc.perform(post("/account/add")
				.content(mapper.writeValueAsBytes(newAcount)))
		.andDo(print())
		.andExpect(status().isCreated());
	}
	
//	@Test
//	public void getTest() {
//		assertThat(restTemplate.getForEntity("http://localhost:"+port+"/account/findAll", List.class)).isNotNull();
//	}
}
