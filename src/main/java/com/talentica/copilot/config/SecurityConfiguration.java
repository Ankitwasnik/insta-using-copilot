package com.talentica.copilot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  // configure security filter chain
  @Bean
  @Order(1)
  SecurityFilterChain configureSecurityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .formLogin()
        .and()
        .authorizeHttpRequests()
        .requestMatchers(HttpMethod.POST, "/v1/users").permitAll()
        .anyRequest().authenticated()
        .and()
        .httpBasic();

    return http.build();
  }
}
