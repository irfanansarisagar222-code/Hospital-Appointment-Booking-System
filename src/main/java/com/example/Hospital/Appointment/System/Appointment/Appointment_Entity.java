package com.example.Hospital.Appointment.System.Appointment;

import com.example.Hospital.Appointment.System.Doctor.Doctor_Entity;
import com.example.Hospital.Appointment.System.Patient.Patient_Entity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
public class Appointment_Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Appointment date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate appointmentDate;

    @NotBlank(message = "Status is required")
    private String status;

    @NotNull(message = "Doctor must be selected")
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor_Entity doctor;

    @NotNull(message = "Patient must be selected")
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient_Entity patient;

    @NotBlank(message = "Symptoms are required")
    private String symptoms;

    @NotBlank(message = "Appointment time is required")
    private String appointmentTime;

    @NotBlank(message = "Priority is required")
    private String priority;


    // Default Constructor
    public Appointment_Entity() {
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(LocalDate appointmentDate) { this.appointmentDate = appointmentDate; }

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