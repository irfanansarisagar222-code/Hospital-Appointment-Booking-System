package com.example.Hospital.Appointment.System.Controller;

import com.example.Hospital.Appointment.System.Appointment.Appointment_repo;
import com.example.Hospital.Appointment.System.Doctor.Doctor_Entity;
import com.example.Hospital.Appointment.System.Doctor.Doctor_repo;
import com.example.Hospital.Appointment.System.Patient.Patient_Entity;
import com.example.Hospital.Appointment.System.Patient.Patient_repo;
import com.example.Hospital.Appointment.System.Services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.Hospital.Appointment.System.Appointment.Appointment_Entity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@Controller
//@RequestMapping("/api/appointments")

public class AppointmentController {
    @Autowired private Appointment_repo appRepo;
    @Autowired private Doctor_repo docRepo;
    @Autowired private Patient_repo patRepo;

}
