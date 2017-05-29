package com.spring.demo.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.spring.demo.security.providers.RestTokenAuthProvider;
import com.spring.demo.security.tokens.RestToken;


public class TokenAuthFilter extends OncePerRequestFilter{

	private static Logger logger = Logger.getLogger(TokenAuthFilter.class); 
	
	private RestTokenAuthProvider restTokenAuthProvider;
	
	public TokenAuthFilter() {
		// TODO Auto-generated constructor stub
	}
	
	public TokenAuthFilter(RestTokenAuthProvider restTokenAuthProvider) {
		this.restTokenAuthProvider=restTokenAuthProvider;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		logger.info("Inside TokenAuthFilter");
		//if(SecurityContextHolder.getContext().getAuthentication()==null){
			if(request.getHeader("X-TOKEN").equals("123")){
				//Authentication authentication=restTokenAuthProvider.authenticate(new RestToken(null));
				SecurityContextHolder.getContext().setAuthentication(new RestToken(null,"raghav"));
				
			}else{
				response.setContentType("application/json");
				String errorResponse = "{'error':'Bad Key'}";
				response.getWriter().write(errorResponse);
			}
		//}
		filterChain.doFilter(request, response);
	}

}
