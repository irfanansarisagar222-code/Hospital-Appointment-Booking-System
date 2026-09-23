package com.example.Hospital.Appointment.System.Signup;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/layout", "/auth/**", "/css/**", "/videos/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form.loginPage("/auth/login")
                        .successHandler((req, res, auth) -> {
                            boolean isAdmin = auth.getAuthorities().stream()
                                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
                            if (isAdmin) res.sendRedirect("/admin/dashboard");
                            else res.sendRedirect("/user/dashboard");
                        })
                );
        return http.build();
    }
}