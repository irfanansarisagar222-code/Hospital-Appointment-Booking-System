package com.example.Hospital.Appointment.System.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller()
public class LayoutController {
        @GetMapping("/")
        public String home() {
            return "layout";
    }
}
