package com.meroproduction.rest.webservices.restfulwebservices.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtTokenAuthorizationOncePerRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService jwtInMemoryUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.http.request.header}")
    private String tokenHeader;

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenAuthorizationOncePerRequestFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
	    throws ServletException, IOException {
	LOGGER.debug("Authentication Request For '{}'", request.getRequestURL());

	final String requestTokenHeader = request.getHeader(this.tokenHeader);

	String username = null;
	String jwtToken = null;
	if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
	    jwtToken = requestTokenHeader.substring(7);
	    try {
		username = jwtTokenUtil.getUsernameFromToken(jwtToken);
	    } catch (IllegalArgumentException e) {
		LOGGER.error("JWT_TOKEN_UNABLE_TO_GET_USERNAME", e);
	    } catch (ExpiredJwtException e) {
		LOGGER.warn("JWT_TOKEN_EXPIRED", e);
	    }
	} else {
	    LOGGER.warn("JWT_TOKEN_DOES_NOT_START_WITH_BEARER_STRING");
	}

	LOGGER.debug("JWT_TOKEN_USERNAME_VALUE '{}'", username);
	if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

	    UserDetails userDetails = this.jwtInMemoryUserDetailsService.loadUserByUsername(username);

	    Boolean validated = jwtTokenUtil.validateToken(jwtToken, userDetails);
	    if (validated != null && validated.equals(Boolean.TRUE)) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
			userDetails, null, userDetails.getAuthorities());
		usernamePasswordAuthenticationToken
			.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	    }
	}

	chain.doFilter(request, response);
    }
}