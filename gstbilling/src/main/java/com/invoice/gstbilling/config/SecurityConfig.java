package com.invoice.gstbilling.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfig {

	@Bean
	public InMemoryUserDetailsManager users(PasswordEncoder encoder) {
		
		UserDetails admin = User.withUsername("admin")
				.password(encoder.encode("admin123"))
				.roles("ADMIN")
				.build();
		
		UserDetails user = User.withUsername("user")
		.password(encoder.encode("user123"))
		.roles("USER")
		.build();
		
		return new InMemoryUserDetailsManager(admin, user);	
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
		.authorizeHttpRequests(auth -> auth
				.requestMatchers("/login", "/css/**").permitAll()
				.requestMatchers("/invoice/**", "/products/**").hasRole("ADMIN")
				.anyRequest().authenticated()
			)
			
			.formLogin(form -> form
				.loginPage("/login")
				.defaultSuccessUrl("/", true)
				.permitAll()
					)
			
			.logout(logout -> logout
				.logoutSuccessUrl("/login?logout")	
				.permitAll()
					);
		
		return http.build();
	}
}
