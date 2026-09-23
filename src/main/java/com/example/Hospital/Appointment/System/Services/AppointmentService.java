package com.example.Hospital.Appointment.System.Services;

import com.example.Hospital.Appointment.System.Appointment.Appointment_Entity;
import com.example.Hospital.Appointment.System.Appointment.Appointment_repo;
import com.example.Hospital.Appointment.System.Doctor.Doctor_repo;
import com.example.Hospital.Appointment.System.Patient.Patient_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    Appointment_repo appointment_repo;

    @Autowired
    private Doctor_repo doctorRepo; // Naya inject kiya

    @Autowired
    private Patient_repo patientRepo; // Naya inject kiya

    // Naya method jo ID ke saath link karega
    public Appointment_Entity bookAppointment(Long doctorId, Long patientId, Appointment_Entity appointment) {
        appointment.setDoctor(doctorRepo.findById(doctorId).orElseThrow());
        appointment.setPatient(patientRepo.findById(patientId).orElseThrow());
        return appointment_repo.save(appointment);
    }

    public List<Appointment_Entity> getAllAppointments() {
        return appointment_repo.findAll();
    }
}
