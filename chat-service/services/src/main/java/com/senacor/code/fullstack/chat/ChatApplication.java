package com.senacor.code.fullstack.chat;

import com.senacor.code.fullstack.chat.security.CORSFilter;
import com.senacor.code.fullstack.chat.security.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class ChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean commonsRequestLoggingFilterRegistration(RequestLoggingFilter filter) {
		return new FilterRegistrationBean(filter);
	}

	@Bean
	public FilterRegistrationBean tokenApiFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new TokenFilter());
		return registration;
	}

	@Bean
	public FilterRegistrationBean corsFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new CORSFilter());
		return registration;
	}
}
