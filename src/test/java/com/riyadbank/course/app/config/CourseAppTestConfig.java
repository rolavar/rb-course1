package com.riyadbank.course.app.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.riyadbank.course.app.repository.account.JdbcAccountRepository;
import com.riyadbank.course.app.repository.account.StubAccountRepository;

//TODO-01: Make this class a configuration class

@Configuration
public class CourseAppTestConfig {
	
	//TODO-02: Expose this method as a bean, the bean name should be dataSource
	public DataSource dataSourceForTest() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		builder.setType(EmbeddedDatabaseType.H2).addScripts("classpath:schema.sql","classpath:data.sql");
		return builder.build();
	}
	
	//TODO-03: Create the properly bean/method to satisfy a repository of account in test class
	public JdbcAccountRepository jdbcAccountRepository() {
		return new JdbcAccountRepository(dataSourceForTest());
	}
	
	//TODO-04: Create the properly bean/method to satisfy anJdbcRepository of account in test class	 
	public StubAccountRepository stubAccountRepository() {
		return new StubAccountRepository();
	}
}
