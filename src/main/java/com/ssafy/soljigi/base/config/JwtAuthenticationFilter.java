package com.ssafy.soljigi.base.config;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.thymeleaf.util.StringUtils;

import com.ssafy.soljigi.base.error.AppException;
import com.ssafy.soljigi.user.service.JwtService;
import com.ssafy.soljigi.user.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	private final JwtService jwtService;
	private final UserService userService;

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request,
		@NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
		throws ServletException, IOException, AppException {
		log.warn("1. JwtAuthenticationFilter : start");
		String authHeader = null;
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equals("token")) {
					authHeader = cookie.getValue();
				}
			}
		}

		final String jwt;
		final String username;
		if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer+")) {
			log.warn("2. JwtAuthenticationFilter : Empty or Not Start Bearer " + authHeader);
			filterChain.doFilter(request, response);
			return;
		}
		jwt = authHeader.substring(7);
		log.warn("3. JwtAuthenticationFilter : Put Out JWT" + authHeader);
		username = jwtService.extractUserName(jwt);

		if (io.micrometer.common.util.StringUtils.isNotEmpty(username)
			&& SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userService.userDetailsService()
				.loadUserByUsername(username);
			if (jwtService.isTokenValid(jwt, userDetails)) {
				SecurityContext context = SecurityContextHolder.createEmptyContext();
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					userDetails, null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				context.setAuthentication(authToken);
				SecurityContextHolder.setContext(context);
			}
		}
		filterChain.doFilter(request, response);
	}
}