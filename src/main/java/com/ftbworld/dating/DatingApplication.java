package com.ftbworld.dating;

import com.ftbworld.dating.filters.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DatingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatingApplication.class, args);
	}

	@Autowired
	AuthFilter authFilter; // We need to autowire this, since the filter relies on the UserRepository.

	@Bean
	public FilterRegistrationBean<AuthFilter> filterRegistrationBean() {
		FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();

		registrationBean.setFilter(authFilter);

		// Filers will be applied to these URLs.
		registrationBean.addUrlPatterns("/api/dating/*");

		return registrationBean;
	}

}
