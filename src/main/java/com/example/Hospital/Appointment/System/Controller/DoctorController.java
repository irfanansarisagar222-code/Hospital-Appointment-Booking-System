package com.example.Hospital.Appointment.System.Controller;

import com.example.Hospital.Appointment.System.Doctor.Doctor_repo;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.Hospital.Appointment.System.Doctor.Doctor_Entity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/api/doctors")

public class DoctorController {
    @Autowired
    Doctor_repo doctor_repo;

}
