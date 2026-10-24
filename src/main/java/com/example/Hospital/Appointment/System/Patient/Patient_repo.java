package com.example.Hospital.Appointment.System.Patient;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Patient_repo extends JpaRepository<Patient_Entity,Long> {
    List<Patient_Entity> findByEmail(String email);
}
