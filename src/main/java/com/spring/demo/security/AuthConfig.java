package com.spring.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.session.ExpiringSession;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

import com.spring.demo.security.filter.CachedRequest;
import com.spring.demo.security.filter.TokenAuthFilter;
import com.spring.demo.security.providers.RestTokenAuthProvider;

@Configuration
//@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled=true)
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400, redisFlushMode = RedisFlushMode.IMMEDIATE)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class AuthConfig extends WebSecurityConfigurerAdapter{
	@Autowired RestTokenAuthProvider restTokenAuthProvider;
	@Autowired SessionAuthStrategy sessionAuthStrategy;
	@Autowired InvalidSessionStrategy invalidSessionStrategy;
	@Autowired FindByIndexNameSessionRepository<? extends ExpiringSession> sessionRepository;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().formLogin().disable();
		
		http
		.addFilterBefore(new CachedRequest(), UsernamePasswordAuthenticationFilter.class)
		.addFilterBefore(new TokenAuthFilter(restTokenAuthProvider), UsernamePasswordAuthenticationFilter.class);
		
		http
		.antMatcher("/**")
		.authorizeRequests()
		.anyRequest().authenticated()
		.and()		
		.sessionManagement().sessionFixation().migrateSession().invalidSessionStrategy(invalidSessionStrategy).sessionAuthenticationStrategy(sessionAuthStrategy);  
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(new RestTokenAuthProvider());
	}
	
	@Bean
	public HttpSessionStrategy httpSessionStrategy() {
		/*CookieHttpSessionStrategy strategy = new CookieHttpSessionStrategy();
		DefaultCookieSerializer serializer = new DefaultCookieSerializer();
		serializer.setCookieName("JSESSIONID");
		strategy.setCookieSerializer(serializer);
		return strategy;*/
		return new HeaderHttpSessionStrategy();
	}
		
	@Bean
	public AuthenticationSuccessHandler authSuccessHandler(){
		return new AuthSuccessHandler();
	}
	
	/*@Bean
    SpringSessionBackedSessionRegistry sessionRegistry() {
            return new SpringSessionBackedSessionRegistry(this.sessionRepository);
    }*/
}
