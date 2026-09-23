package com.example.Hospital.Appointment.System.Signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private User_Service userService;
    @Autowired private User_repo userRepo;
    @Autowired private BCryptPasswordEncoder encoder;

    @GetMapping("/signup")
    public String showSignup() { return "signup"; }

    @PostMapping("/send-otp")
    public String sendOtp(@RequestParam String email, Model model) {
        userService.initiateRegistration(email);
        model.addAttribute("email", email);
        return "verify-otp";
    }

    @PostMapping("/verify")
    public String verify(@RequestParam String email, @RequestParam String otp, @RequestParam String password) {
        return userRepo.findByEmail(email).map(user -> {
            if (user.getOtp().equals(otp) && LocalDateTime.now().isBefore(user.getOtpCreatedAt().plusMinutes(5))) {
                user.setPassword(encoder.encode(password));
                user.setVerified(true);

                // Yahan role assign karo (Service ka logic yahan use karo)
                if (email.equals("irfanansarisagar222@gmail.com")) {
                    user.setRole("ROLE_ADMIN");
                } else {
                    user.setRole("ROLE_USER");
                }

                userRepo.save(user);
                return "redirect:/auth/login";
            }
            return "redirect:/auth/signup?error=invalid";
        }).orElse("redirect:/auth/signup?error=invalid");
    }
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // templates/login.html
    }
}
