package dev.daniel.BooksGalore.config;

import dev.daniel.BooksGalore.Model.Role;
import dev.daniel.BooksGalore.Model.User;
import dev.daniel.BooksGalore.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.net.http.HttpRequest;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class securityConfig{

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                    .disable()
                .authorizeHttpRequests()
                .requestMatchers("/admin.html").hasAnyAuthority("ADMIN")
                    .requestMatchers("/*.html").permitAll()
                    .requestMatchers("/js/**").permitAll()
                    .requestMatchers("/css/**").permitAll()
                    .requestMatchers("/fonts/**").permitAll()
                    .requestMatchers("/icon/**").permitAll()
                    .requestMatchers("/images/**").permitAll()
                    .requestMatchers("/api/v1/user/**").permitAll()
                    .requestMatchers("/api/v1/registration").permitAll()
                    .requestMatchers("/api/v1/public/books/**").permitAll()
                    .requestMatchers("/api/v1/books/**").hasAnyAuthority("USER")
                    .requestMatchers("/api/v1/cart/**").hasAnyAuthority("USER")
                    .requestMatchers("/api/v1/admin/**").hasAnyAuthority("ADMIN")
                    .anyRequest()
                    .authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/loginProcess")
                .successHandler((request, response, authentication) -> {
                    if (authentication.getPrincipal() instanceof User userDetails) {
                        if (userDetails.getRole().equals(Role.ADMIN)) {
                            response.sendRedirect("/admin.html"); // Redirect admin to admin dashboard
                            return;
                        }
                    }
                    response.sendRedirect("/index.html"); // Redirect regular user to index page
                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logoutProcess")
                .deleteCookies("JSESSIONID");

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }



}
