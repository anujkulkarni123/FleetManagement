package com.exavalu.fleetmanagementapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService getDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(getDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                .requestMatchers("/register", "/userRegistration", "/error", "/error2", "/forgetPassword",
                        "/forgetPasswordCreateNew", "/forgotPasswordUpdate")
                .permitAll()
                .requestMatchers("/assets/**", "/static/**", "/vendor/**", "/img/**", "/js/**")
                .permitAll()
                .requestMatchers("/dummy").hasRole("User")
                .anyRequest().authenticated())
            .formLogin(login -> login
                .loginPage("/login") // Path to your custom login page
                .permitAll()
                .defaultSuccessUrl("/", true))  // Always redirect to "/" after login
            .logout(logout -> logout.permitAll());

        return http.build();
    }
}
