package com.arunabha.springboot.rms.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
/*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(appUserDetailsService);
    }
*/
	
	
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
        		.authorizeRequests()
                .antMatchers(HttpMethod.POST,"/user-register").permitAll()
                .antMatchers(HttpMethod.POST,"/user-login").permitAll()
        		.antMatchers(HttpMethod.POST,"/user-delete").permitAll()
        		.antMatchers(HttpMethod.POST,"/user-update").permitAll();
    }

   /* @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }*/
}
