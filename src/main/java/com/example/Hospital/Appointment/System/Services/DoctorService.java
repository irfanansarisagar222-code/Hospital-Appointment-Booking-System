package com.example.Hospital.Appointment.System.Services;

import com.example.Hospital.Appointment.System.Doctor.Doctor_Entity;
import com.example.Hospital.Appointment.System.Doctor.Doctor_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
        @Autowired
        Doctor_repo doctor_repo;

        public Doctor_Entity addDoctor(Doctor_Entity doctor){
            return doctor_repo.save(doctor);
        }

        public List<Doctor_Entity> getAllDoctors(){
            return doctor_repo.findAll();
        }


}
