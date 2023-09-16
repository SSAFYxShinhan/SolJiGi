package com.ssafy.soljigi.base.config;

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

import com.ssafy.soljigi.base.config.custom.CustomAccessDeniedHandler;
import com.ssafy.soljigi.base.config.custom.CustomAuthenticationEntryPoint;
import com.ssafy.soljigi.base.config.custom.JwtExceptionFilter;
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

	private static final String[] GET_AUTHENTICATED = {
		"/view/main",
		"/game",
			"/diagnosis",
			"/view/game/result/**"
	};
	

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		log.warn("1. into filterchain");
		http.csrf(AbstractHttpConfigurer::disable);

		http.authorizeHttpRequests(request ->
			request.requestMatchers(HttpMethod.POST, POST_AUTHENTICATED).permitAll()
				.requestMatchers(HttpMethod.GET, GET_AUTHENTICATED).authenticated()
					.requestMatchers(HttpMethod.GET, "/view/game/result/**").authenticated()
				.anyRequest().permitAll());

		log.warn("2. authorizeHttpRequests");

		// .exceptionHandling(manager ->
		// 	manager.accessDeniedHandler(new CustomAccessDeniedHandler()))
		http.exceptionHandling(manager ->
			manager.authenticationEntryPoint(new CustomAuthenticationEntryPoint())
				.accessDeniedHandler(new CustomAccessDeniedHandler()));

		log.warn("3. exceptionHanding");

		http.sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS));

		http.authenticationProvider(authenticationProvider());

		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(jwtExceptionFilter, JwtAuthenticationFilter.class);

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