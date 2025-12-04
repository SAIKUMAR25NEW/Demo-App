package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public org.springframework.security.core.userdetails.UserDetailsService userDetailsService() {

        var user = org.springframework.security.core.userdetails.User
                .withUsername("admin")
                .password("{noop}password123")
                .roles("ADMIN")
                .build();

        return new org.springframework.security.provisioning.InMemoryUserDetailsManager(user);
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/employees", "/employees/**").permitAll()    // allow GET operations
                        .requestMatchers("/employees").hasRole("ADMIN")                // restrict POST
                        .requestMatchers("/employees/**").hasRole("ADMIN")             // restrict PUT, DELETE
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        // enable basic authentication

        return http.build();
    }
}
