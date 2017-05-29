package com.spring.demo.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

//@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler{

	private static Logger logger = Logger.getLogger(AuthSuccessHandler.class); 
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		logger.info("Entering AuthSuccessHandler");
	}
	
}
