package com.stynt.configuration.dbconfiguration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class InfoDBConfig {

	private static final String DRIVER_CLASS_NAME = "spring.info.datasource.driver-class-name";
	private static final String DATABASE_URL = "spring.info.datasource.url";
	private static final String DATABASE_USERNAME = "spring.info.datasource.username";
	private static final String DATABASE_PASSWORD = "spring.info.datasource.password";
	private static final String DATABASE_PREFIX = "spring.info.datasource";
	
	@Autowired
	private Environment env;

	@Bean(name = "tempdaddy")
	@ConfigurationProperties(prefix = DATABASE_PREFIX)
	public DataSource dataSource() {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName(env.getProperty(DRIVER_CLASS_NAME));
		dataSource.setUrl(env.getProperty(DATABASE_URL));
		dataSource.setUsername(env.getProperty(DATABASE_USERNAME));
		dataSource.setPassword(env.getProperty(DATABASE_PASSWORD));

		return dataSource;
	}

	@Bean(name = "namedParameterJdbcTemplate")
	public NamedParameterJdbcTemplate template(@Qualifier("tempdaddy") DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Bean(name = "jdbcTemplate")
	public JdbcTemplate namedParameterTemplate(@Qualifier("tempdaddy") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}
