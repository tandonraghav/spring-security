package com.spring.demo.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.session.ExpiringSession;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.stereotype.Component;

@Component
public class InvalidSessionStrategy implements org.springframework.security.web.session.InvalidSessionStrategy{

	private static Logger logger = Logger.getLogger(InvalidSessionStrategy.class); 
	
	
	@Override
	public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		logger.info("Entering InvalidSessionStrategy");	
	}

}
