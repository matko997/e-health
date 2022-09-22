package com.zavrsnirad.e_zdravlje.security;

import com.zavrsnirad.e_zdravlje.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    private final CustomUserDetailsService customUserDetailsService;

    public WebSecurityConfig(CustomAuthenticationFailureHandler customAuthenticationFailureHandler, CustomUserDetailsService customUserDetailsService) {
        this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
        this.customUserDetailsService = customUserDetailsService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**", "/registracija", "/").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/prijava")
                .usernameParameter("email")
                .defaultSuccessUrl("/naslovnica")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/odjava")
                .logoutSuccessUrl("/index")
                .permitAll()
                .and().csrf().disable(); // we'll enable this in a later blog post
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}