package com.example.Hospital.Appointment.System.Signup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/index")
    public String showDashboard() {
        return "index"; // Ye templates/index.html file ko kholega
    }
}
