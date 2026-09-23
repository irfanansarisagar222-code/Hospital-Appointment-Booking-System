package com.example.Hospital.Appointment.System.Patient;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Patient_repo extends JpaRepository<Patient_Entity,Long> {
    Patient_Entity findByEmail(String email);
}
