package com.ssafy.soljigi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
public class SpringSecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder(){
		return new SimplePasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configure(http));
		http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.configure(http));
		http.authorizeHttpRequests(request -> request
				.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
//				.requestMatchers("/status", "/images/**", "/view/join", "/auth/join").permitAll()
				.anyRequest().authenticated())
			.formLogin(login -> login
				.loginPage("/view/login")
				.loginProcessingUrl("/login-process")
				.usernameParameter("userid")
				.passwordParameter("pw")
				.defaultSuccessUrl("/view/dashboard", true)
				.permitAll())
			.logout(Customizer.withDefaults());
		return http.build();
	}
}