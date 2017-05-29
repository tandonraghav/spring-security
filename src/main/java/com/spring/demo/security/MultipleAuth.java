/*package com.spring.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HttpSessionStrategy;

import com.spring.demo.security.filter.TokenAuthFilter;
import com.spring.demo.security.providers.RestTokenAuthProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled=true)
@EnableRedisHttpSession
public class MultipleAuth{
	
	@Configuration
	@Order(1)
	public static class AuthConfig extends WebSecurityConfigurerAdapter{
		
		public AuthConfig() {
			super(true);
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception{
			http
			.antMatcher("/**")
			.authorizeRequests()
			.anyRequest().authenticated()
			.and()
			.addFilterBefore(new TokenAuthFilter(), UsernamePasswordAuthenticationFilter.class)
			//.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.csrf().disable().httpBasic().disable();	  
		}
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.authenticationProvider(new RestTokenAuthProvider());
		}
	}
	
	@Configuration
	@Order(2)
	public static class AuthConfig2 extends WebSecurityConfigurerAdapter{
		
		public AuthConfig2() {
			super(true);
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception{
			http
			.antMatcher("post")
			.authorizeRequests()
			.anyRequest().authenticated()
			.and()
			.addFilterBefore(new TokenAuthFilterPost(), UsernamePasswordAuthenticationFilter.class)
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().csrf().disable().httpBasic().disable();	  
		}
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.authenticationProvider(new RestTokenAuthProvider());
		}
	}
	
	@Bean
	public HttpSessionStrategy httpSessionStrategy() {
		CookieHttpSessionStrategy strategy = new CookieHttpSessionStrategy();
		DefaultCookieSerializer serializer = new DefaultCookieSerializer();
		serializer.setCookieName("JSESSIONID");
		strategy.setCookieSerializer(serializer);
		return strategy;
	}
}


*/