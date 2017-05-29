package com.spring.demo.security;

import java.security.Principal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.session.ExpiringSession;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.stereotype.Component;

@Component
public class SessionAuthStrategy implements SessionAuthenticationStrategy{

	private static Logger logger = Logger.getLogger(SessionAuthStrategy.class); 
	@Autowired FindByIndexNameSessionRepository<? extends ExpiringSession> sessionRepository;
	@Override
	public void onAuthentication(Authentication authentication, HttpServletRequest request,
			HttpServletResponse response) throws SessionAuthenticationException {
		logger.info("Entering sessionAuthstrategy");
		/*HttpSession session=request.getSession();
		String username=String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		//Principal p=(Principal)authentication.getPrincipal();
		logger.info("username="+username);
		
		Collection<? extends ExpiringSession> userSessions = sessionRepository
				.findByIndexNameAndIndexValue(
				FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME,username).values();
		
		logger.info("userSessionsSize="+userSessions.size());
		
		for(ExpiringSession userSession : userSessions){
			logger.info(userSession.getId());
			sessionRepository.delete(userSession.getId());
		}*/
	}

}
