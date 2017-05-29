package com.spring.demo.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.spring.demo.security.tokens.RestToken;

public class TokenAuthFilterPost extends OncePerRequestFilter{

	private static Logger logger = Logger.getLogger(TokenAuthFilterPost.class); 
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		logger.info("Inside TokenAuthFilterPost");
		if(request.getHeader("X-TOKEN")=="123"){
			SecurityContextHolder.getContext().setAuthentication(new RestToken(null,"raghav"));
			filterChain.doFilter(request, response);
		}else{
			response.setContentType("application/json");
			String errorResponse = "{'error':'Bad Key'}";
			response.getWriter().write(errorResponse);
		}
	}

}
