package com.example.Hospital.Appointment.System.Services;

import com.example.Hospital.Appointment.System.Patient.Patient_Entity;
import com.example.Hospital.Appointment.System.Patient.Patient_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    Patient_repo patient_repo;

    public Patient_Entity addPatient(Patient_Entity patient){
        return patient_repo.save(patient);
    }

    public List<Patient_Entity> getAllPatients(){
        return patient_repo.findAll();
    }
}
