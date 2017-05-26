package com.spring.demo.security.providers;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.spring.demo.security.tokens.RestToken;

public class RestTokenAuthProvider implements AuthenticationProvider{

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		return authentication;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(RestToken.class);
	}

}
