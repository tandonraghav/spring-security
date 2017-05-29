package com.spring.demo.security.providers;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.spring.demo.security.AuthConfig;
import com.spring.demo.security.filter.TokenAuthFilter;
import com.spring.demo.security.tokens.RestToken;

@Component
public class RestTokenAuthProvider implements AuthenticationProvider{

	private static Logger logger = Logger.getLogger(RestTokenAuthProvider.class); 
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		logger.info("Inside RestTokenAuthProvider");
		return authentication;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(RestToken.class);
	}

}
