package com.example.Hospital.Appointment.System.Signup;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface User_repo extends JpaRepository<User_Entity,Long> {
    Optional<User_Entity> findByEmail(String email);
}
