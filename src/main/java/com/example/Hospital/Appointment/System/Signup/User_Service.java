package com.example.Hospital.Appointment.System.Signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class User_Service {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private User_repo userRepo;

    public void initiateRegistration(String email) {
        Optional<User_Entity> existingUser = userRepo.findByEmail(email);
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists!");
        }
        String otp = String.valueOf((int) (Math.random() * 900000) + 100000);
        User_Entity user = new User_Entity();
        user.setEmail(email);
        user.setOtp(otp);user.setOtpCreatedAt(LocalDateTime.now()); // Yahan time set ho raha hai
        user.setVerified(false);
        userRepo.save(user);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("OTP Verification");
        message.setText("Your verification code is: " + otp+ " It will Expire in 5 minutes");
        mailSender.send(message);

        if (email.equals("irfanansarisagar222@gmail.com")) {
            user.setRole("ROLE_ADMIN");
        } else {
            user.setRole("ROLE_USER");
        }
        userRepo.save(user);
    }
}
