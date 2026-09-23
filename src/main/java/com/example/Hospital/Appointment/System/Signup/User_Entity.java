package com.example.Hospital.Appointment.System.Signup;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class User_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true)
    String email;
    String password;
    String otp;
    private LocalDateTime otpCreatedAt;
    boolean verified=false;
    private String role; // "ROLE_USER" ya "ROLE_ADMIN"

    @Column(length = 255)
    private String profilePhoto;


    // Getters and Setters
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public User_Entity() {
    }

    public LocalDateTime getOtpCreatedAt() {
        return otpCreatedAt;
    }

    public void setOtpCreatedAt(LocalDateTime otpCreatedAt) {
        this.otpCreatedAt = otpCreatedAt;
    }

    public User_Entity(LocalDateTime otpCreatedAt) {
        this.otpCreatedAt = otpCreatedAt;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public User_Entity(Long id, String email, String password, String otp, boolean verified) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.otp = otp;
        this.verified = verified;
    }
}
