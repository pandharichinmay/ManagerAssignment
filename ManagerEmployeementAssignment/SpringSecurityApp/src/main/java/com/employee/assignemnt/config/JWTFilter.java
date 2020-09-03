package com.employee.assignemnt.config;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.employee.assignemnt.model.ManagerDetails;
import com.employee.assignemnt.service.impl.ManagerDetailsService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

public class JWTFilter extends OncePerRequestFilter {
	private static final String HEADER_STRING = null;

	private static final String TOKEN_PREFIX = null;

	@Autowired
	private ManagerDetailsService managerDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader(HEADER_STRING);
		String email = null;
		String authToken = null;
		if (header != null && header.startsWith(TOKEN_PREFIX)) {
			authToken = header.replace(TOKEN_PREFIX, "");
			try {
				email = jwtTokenUtil.getEmailFromToken(authToken);
			} catch (IllegalArgumentException e) {
				logger.error("an error occured during getting email from token", e);
			} catch (ExpiredJwtException e) {
				logger.warn("the token is expired and not valid anymore", e);
			} catch (SignatureException e) {
				logger.error("Authentication Failed. Email or Password not valid.");
			}
		} else {
			logger.warn("couldn't find bearer string, will ignore the header");
		}
		if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			ManagerDetails managerDetails = managerDetailsService.loadManagerByEmail(email);

			if (jwtTokenUtil.validateToken(authToken, managerDetails)) {
				EmailPasswordAuthenticationToken authentication = new EmailPasswordAuthenticationToken(managerDetails,
						null, Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
				logger.info("authenticated user " + email + ", setting security context");
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		chain.doFilter(req, res);
	}
}
