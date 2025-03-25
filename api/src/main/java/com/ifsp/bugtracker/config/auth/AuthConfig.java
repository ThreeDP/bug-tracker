package com.ifsp.bugtracker.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AuthConfig {

    private static final String ISSUE_API = "/api/v1/issues";

    @Autowired
    SecurityFilter secFilter;

    @Bean
    SecurityFilterChain securityFIlterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // Users
                .requestMatchers(HttpMethod.POST, "/api/v1/users/**").hasRole("MODERATOR")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/users/**").hasRole("MODERATOR")
                .requestMatchers(HttpMethod.PUT, "/api/v1/users**").hasRole("CUSTOMER")
                .requestMatchers(HttpMethod.GET, "/api/v1/users**").hasRole("CUSTOMER")
                .requestMatchers(HttpMethod.GET, "/api/v1/users/**").hasRole("CUSTOMER")
                .requestMatchers(HttpMethod.GET, "/api/v1/users/*").hasRole("CUSTOMER")
                .requestMatchers(HttpMethod.GET, "/api/v1/users").hasRole("CUSTOMER")
                // Auth
                .requestMatchers(HttpMethod.POST, "/api/v1/auth/register").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
                // Issue
                .requestMatchers(HttpMethod.POST, ISSUE_API + "/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, ISSUE_API + "/**").permitAll()
                .anyRequest()
                .authenticated()
            )
            .addFilterBefore(secFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
        throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
