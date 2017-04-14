package edu.pl.pollub;

import edu.pl.pollub.config.SecurityConfig;
import edu.pl.pollub.config.StorageProperties;
import edu.pl.pollub.service.implementation.UserDetailsServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class JustMemesApplication {
	@Bean
	public WebSecurityConfigurerAdapter webSecurityConfigurerAdapter() {
		return new SecurityConfig();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(11);
	}

	@Bean
	public SessionRegistryImpl sessionRegistry() {
		return new SessionRegistryImpl();
	}

	@Bean
	public UserDetailsServiceImpl userDetailsService() {
		return new UserDetailsServiceImpl();
	}

	public static void main(String[] args) {
		SpringApplication.run(JustMemesApplication.class, args);
	}


}
