package com.example.Hospital.Appointment.System.Signup;

import com.example.Hospital.Appointment.System.Appointment.Appointment_Entity;
import com.example.Hospital.Appointment.System.Appointment.Appointment_repo;
import com.example.Hospital.Appointment.System.Doctor.Doctor_Entity;
import com.example.Hospital.Appointment.System.Doctor.Doctor_repo;
import com.example.Hospital.Appointment.System.Patient.Patient_Entity;
import com.example.Hospital.Appointment.System.Patient.Patient_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    User_repo user_repo;
    @Autowired
    Doctor_repo doctor_repo;
    @Autowired
    Patient_repo patient_repo;
    @Autowired
    Appointment_repo appointment_repo;

    @GetMapping("/dashboard")
    public String userDashboard() {
        return "user-dashboard"; // templates folder mein user-dashboard.html honi chahiye
    }

    //Appointment Controller Code Merge
    @GetMapping("/book-appointment")
    public String showBookingForm(Model model) {
        // 1. Dropdown ke liye lists bhejo
        model.addAttribute("doctors", doctor_repo.findAll());
        model.addAttribute("patients", patient_repo.findAll());

        // 2. IMPORTANT: Ye object bhejna hi padega warna error aayegi
        model.addAttribute("appointment", new Appointment_Entity());

        return "Appointmentform"; // Tumhari HTML file ka exact naam
    }

    // Form submit hone par appointment save hoga
    @PostMapping("/appointments/save")
    public String saveAppointment(@RequestParam("doctorId") Long doctorId,
                                  @RequestParam("patientId") Long patientId,
                                  @ModelAttribute Appointment_Entity appointment,
                                  Model model) { // 1. RedirectAttributes की जगह Model का उपयोग करें

        Doctor_Entity doc = doctor_repo.findById(doctorId).orElseThrow();
        Patient_Entity pat = patient_repo.findById(patientId).orElseThrow();

        appointment.setDoctor(doc);
        appointment.setPatient(pat);
        appointment.setStatus("Pending");

        appointment_repo.save(appointment);

        model.addAttribute("successMessage", "Appointment booked successfully!");

        return "redirect:/user/dashboard";
    }
    // Form dikhane ke liye
    @GetMapping("/add-patient")
    public String showForm() {
        return "Patientform";
    }

    // Data save karne ke liye
    @PostMapping("/patients/save")
    public String savePatient(@ModelAttribute Patient_Entity patient, RedirectAttributes redirectAttributes) {
        patient_repo.save(patient);
        redirectAttributes.addFlashAttribute("successMessage", "Record Saved Successfully!");
        return "redirect:/user/add-patient";
    }
    @GetMapping("/update-patient")
    public String showUpdatePage(@RequestParam(value = "id", required = false) Long id, Model model) {
        model.addAttribute("allPatients", patient_repo.findAll());

        if (id != null) {
            // ID ke base pe pura data database se nikala
            Patient_Entity patient = patient_repo.findById(id).orElse(new Patient_Entity());
            model.addAttribute("patient", patient);
        } else {
            model.addAttribute("patient", new Patient_Entity());
        }
        return "update-page";
    }

    @PostMapping("/update-patient/{id}")
    public String updatePatient(@PathVariable("id") Long id,
                                @ModelAttribute Patient_Entity updatedPatient,
                                Model model) {

        Patient_Entity existing = patient_repo.findById(id).orElseThrow();

        existing.setName(updatedPatient.getName());
        existing.setEmail(updatedPatient.getEmail());
        existing.setPhoneNumber(updatedPatient.getPhoneNumber());
        existing.setAge(updatedPatient.getAge());
        existing.setGender(updatedPatient.getGender());

        patient_repo.save(existing);

        model.addAttribute("successMessage", "Record Updated Successfully!");
        model.addAttribute("patient", existing);
        model.addAttribute("allPatients", patient_repo.findAll());
        return "update-page";
    }
    @GetMapping("/my-appointments")
    public String getMyBookings(Model model, Principal principal) {
        String email = principal.getName();
        System.out.println("DEBUG: Logged-in Email: " + email);

        Patient_Entity currentPatient = patient_repo.findByEmail(email);

        if (currentPatient == null) {
            System.out.println("DEBUG: Database mein is email se koi patient nahi mila: " + email);
            model.addAttribute("error", "Patient profile not found!");
            return "my-bookings";
        }

        System.out.println("DEBUG: Patient Found! ID: " + currentPatient.getId());
        model.addAttribute("myAppointments", appointment_repo.findByPatientId(currentPatient.getId()));
        return "my-bookings";
    }

    @PostMapping("/upload-photo")
    public String uploadProfilePhoto(@RequestParam("file") MultipartFile file, Principal principal) {
        if (file.isEmpty()) return "redirect:/user/dashboard?error";

        try {
            // 1. Static folder mein path set karo
            String uploadDir = "src/main/resources/static/uploads/";
            Files.createDirectories(Paths.get(uploadDir));

            // 2. Unique filename
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            // 3. File Copy
            Path path = Paths.get(uploadDir + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            // 4. Database update (Optional check)
            User_Entity user = user_repo.findByEmail(principal.getName()).orElse(null);
            if(user != null) {
                user.setProfilePhoto(fileName);
                user_repo.save(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/user/dashboard";
    }
}
