package com.example.Hospital.Appointment.System.Appointment;

import com.example.Hospital.Appointment.System.Doctor.Doctor_Entity;
import com.example.Hospital.Appointment.System.Patient.Patient_Entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
public class Appointment_Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ...
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime appointmentDate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor_Entity doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient_Entity patient;

    private String symptoms;

    private String appointmentTime;
    private String priority;


    // Default Constructor
    public Appointment_Entity() {
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(LocalDateTime appointmentDate) { this.appointmentDate = appointmentDate; }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Doctor_Entity getDoctor() { return doctor; }
    public void setDoctor(Doctor_Entity doctor) { this.doctor = doctor; }

    public Patient_Entity getPatient() { return patient; }
    public void setPatient(Patient_Entity patient) { this.patient = patient; }
}