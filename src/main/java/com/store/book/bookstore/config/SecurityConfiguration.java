package com.store.book.bookstore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()

                .withUser("user1").password("{noop}pwd1").roles("USER").and()
                .withUser("user2").password("{noop}pwd2").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("**/removebook/**").hasAuthority("ADMIN")
                .anyRequest()
                .fullyAuthenticated()
                .and()
                .httpBasic();
        httpSecurity.csrf().disable();

    }

}