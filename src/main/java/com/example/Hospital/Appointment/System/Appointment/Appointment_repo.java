package com.example.Hospital.Appointment.System.Appointment;

import com.example.Hospital.Appointment.System.Patient.Patient_Entity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Appointment_repo extends JpaRepository<Appointment_Entity,Long> {
    // Appointment_repo.java mein ye hona chahiye
    List<Appointment_Entity> findByPatientEmail(String email);
    List<Appointment_Entity> findByPatientId(Long patientId);}
