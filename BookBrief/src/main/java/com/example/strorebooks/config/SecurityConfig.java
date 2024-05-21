package com.example.strorebooks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(
                                "/deleteBook/{id}","/my_books", "/book_register","/save","/editBook/{id}","/mylist/{id}","/deleteMyList/{id}", "/login", "/users").authenticated()
                        .requestMatchers( "/","/available_books","/registration", "/webjars/**", "images/2.jpg","/**","images/3.jpg").permitAll())
                .formLogin((form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/",true)
                        .permitAll()))
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails adminmanager = User.withUsername("adminmanager")
                .password("12345")
                .roles("managetable", "delete", "update")
                //.roles("admin")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password("12345")
                //.authorities("admin")
                .roles("admin")
                .build();
        UserDetails user = User.withUsername("user")
                .password("12345")
                .roles("user")
                .build();
        return new InMemoryUserDetailsManager(adminmanager, admin, user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}