package com.example.Hospital.Appointment.System.Signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private User_repo repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User_Entity user = repo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().replace("ROLE_", ""))
                .build();
    }
}