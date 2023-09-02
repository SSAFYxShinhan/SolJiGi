package com.ssafy.soljigi.user.service.impl;

import java.io.IOException;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.soljigi.user.dto.response.ErrorResponse;
import com.ssafy.soljigi.user.dto.response.Response;
import com.ssafy.soljigi.user.error.AppException;
import com.ssafy.soljigi.user.error.ErrorCode;
import com.ssafy.soljigi.user.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JwtServiceImpl implements JwtService {
	// @Value("${token.signing.key}")s
	private String jwtSigningKey = "413F4428472B4B6250655368566D5970337336763979244226452948404D6351";

	@Override
	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	@Override
	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(), userDetails);
	}

	@Override
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String userName = extractUserName(token);
		return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
		final Claims claims;
		try {
			claims = extractAllClaims(token);
		} catch (ExpiredJwtException e) {
			throw new AppException(ErrorCode.EXPIRED_TOKEN);
		}
		return claimsResolvers.apply(claims);
	}

	private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
		return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
			.signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	private Claims extractAllClaims(String token) throws ExpiredJwtException {
		return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
			.getBody();
	}

	private Key getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public static void MakeError(HttpServletResponse response, ErrorCode errorCode) throws IOException {
		response.setContentType("application/json;charset=UTF-8");
		response.setStatus(errorCode.getHttpStatus().value());
		ObjectMapper objectMapper = new ObjectMapper();

		ErrorResponse errorResponse = new ErrorResponse(errorCode, errorCode.getMessage());
		Response<ErrorResponse> resultResponse = Response.error(errorResponse);

		// 한글 출력을 위해 getWriter()
		response.getWriter().write(objectMapper.writeValueAsString(resultResponse));
	}

}