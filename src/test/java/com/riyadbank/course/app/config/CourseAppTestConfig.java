package com.riyadbank.course.app.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

//TODO-01: Make this class a configuration class

@Configuration
@ComponentScan(basePackages = {"com.riyadbank.course.app"})
public class CourseAppTestConfig {
	
	//TODO-02: Expose this method as a bean, the bean name should be dataSource
	@Bean
	public DataSource dataSourceForTest() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		builder.setType(EmbeddedDatabaseType.H2).addScripts("classpath:schema.sql","classpath:data.sql");
		return builder.build();
	}
	
	//TODO-03: Create the properly bean/method to satisfy a repository of account in test class
//	@Bean
//	public JdbcAccountRepository jdbcAccountRepository() {
//		return new JdbcAccountRepository(dataSourceForTest());
//	}
//	
//	//TODO-04: Create the properly bean/method to satisfy anJdbcRepository of account in test class	 
//	@Bean
//	public StubAccountRepository stubAccountRepository() {
//		return new StubAccountRepository();
//	}
}
