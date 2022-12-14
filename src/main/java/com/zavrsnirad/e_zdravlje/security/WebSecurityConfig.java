package com.zavrsnirad.e_zdravlje.security;

import com.zavrsnirad.e_zdravlje.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final CustomUserDetailsService customUserDetailsService;

  public WebSecurityConfig(CustomUserDetailsService customUserDetailsService) {
    this.customUserDetailsService = customUserDetailsService;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/css/**", "/registracija", "/")
        .permitAll()
        .antMatchers(HttpMethod.POST, "/doktori")
        .hasAuthority("ADMIN")
        .antMatchers(HttpMethod.DELETE, "/*/obrisi")
        .hasAnyAuthority("ADMIN", "DOCOTR")
        .antMatchers(HttpMethod.PUT, "/*/uredi")
        .hasAnyAuthority("ADMIN", "DOCOTR")
        .antMatchers(
            HttpMethod.POST, "/cjepiva", "/alergije", "/krvni-tlakovi", "/dijabetesi", "lab-nalazi")
        .hasAnyAuthority("ADMIN", "DOCOTR")
        .antMatchers("/statistika")
        .hasAuthority("ADMIN")
        .anyRequest()
        .authenticated()
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
        .permitAll();
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
