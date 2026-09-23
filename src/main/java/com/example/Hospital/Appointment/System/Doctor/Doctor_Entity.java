package com.example.Hospital.Appointment.System.Doctor;

import jakarta.persistence.*;

@Entity
public class Doctor_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String specialization;
    String experience;
    String phone;
    String email;
    String chamberNumber;
    Double consultationFee;
    @Column(name = "working_hours")
    private String workingHours; // e.g., "10 AM - 2 PM"

    public Doctor_Entity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Doctor_Entity(String experience) {
        this.experience = experience;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getChamberNumber() {
        return chamberNumber;
    }

    public void setChamberNumber(String chamberNumber) {
        this.chamberNumber = chamberNumber;
    }

    public Double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(Double consultationFee) {
        this.consultationFee = consultationFee;
    }

    public Doctor_Entity(String chamberNumber, Double consultationFee) {
        this.chamberNumber = chamberNumber;
        this.consultationFee = consultationFee;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public Doctor_Entity(String email, String phone) {
        this.email = email;
        this.phone = phone;
    }

    public Doctor_Entity(Long id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }
}
