package com.ssafy.soljigi.user.config;

import static org.springframework.security.config.http.SessionCreationPolicy.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ssafy.soljigi.user.config.custom.CustomAccessDeniedHandler;
import com.ssafy.soljigi.user.config.custom.CustomAuthenticationEntryPoint;
import com.ssafy.soljigi.user.config.custom.JwtExceptionFilter;
import com.ssafy.soljigi.user.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Log4j2
public class SecurityConfiguration {
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final UserService userService;
	private final JwtExceptionFilter jwtExceptionFilter;
	private static final String[] POST_AUTHENTICATED = {
		"/api/v1/auth/**"
	};

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		log.warn("1. into filterchain");
		http.csrf(AbstractHttpConfigurer::disable);

		http.authorizeHttpRequests(request ->
			request.requestMatchers(HttpMethod.POST, POST_AUTHENTICATED).permitAll()
				.requestMatchers(HttpMethod.GET, "/view/main").permitAll()
				.requestMatchers(HttpMethod.GET, "/view/white_error").permitAll()
				.anyRequest().authenticated());

		log.warn("2. authorizeHttpRequests");

		http.exceptionHandling(manager ->
				manager.accessDeniedHandler(new CustomAccessDeniedHandler()))
			.exceptionHandling(manager ->
				manager.authenticationEntryPoint(new CustomAuthenticationEntryPoint()))
			.exceptionHandling(manager ->
				manager.accessDeniedPage("/view/white_error"));

		log.warn("3. exceptionHanding");

		http.sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
			.authenticationProvider(authenticationProvider())
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(jwtExceptionFilter, JwtAuthenticationFilter.class);

		log.warn("4. finish filter");

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userService.userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
		throws Exception {
		return config.getAuthenticationManager();
	}
}