package com.example.Hospital.Appointment.System.Controller;

import com.example.Hospital.Appointment.System.Patient.Patient_Entity;
import com.example.Hospital.Appointment.System.Patient.Patient_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class PatientController {
    @Autowired
    Patient_repo patient_repo;

}
