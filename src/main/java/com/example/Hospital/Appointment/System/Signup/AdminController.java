package com.example.Hospital.Appointment.System.Signup;

import com.example.Hospital.Appointment.System.Appointment.Appointment_Entity;
import com.example.Hospital.Appointment.System.Appointment.Appointment_repo;
import com.example.Hospital.Appointment.System.Doctor.Doctor_Entity;
import com.example.Hospital.Appointment.System.Doctor.Doctor_repo;
import com.example.Hospital.Appointment.System.Patient.Patient_Entity;
import com.example.Hospital.Appointment.System.Patient.Patient_repo;
import com.example.Hospital.Appointment.System.Services.PdfService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    Doctor_repo doctor_repo;
    @Autowired
    Patient_repo patient_repo;
    @Autowired
    Appointment_repo appointment_repo;
    @Autowired
    PdfService pdfService;

    @GetMapping("/dashboard")
    public String adminDashboard() {
        return "admin-dashboard"; // templates folder mein admin-dashboard.html honi chahiye
    }
    //Doctor Controller Code Merge
    @GetMapping("/add-doctor")
    public String adddoctor(){
        return "Doctorform";
    }

    // Form ka data save karne ke liye
    @PostMapping("/doctors/save")
    public String saveDoctor(@ModelAttribute Doctor_Entity doctor) {
        doctor_repo.save(doctor); // Ye data database mein dal dega
        return "redirect:/admin/dashboard"; // Save hone ke baad list page par bhej dega
    }
    @GetMapping("/doctors")
    public String listDoctors(Model model) {
        model.addAttribute("doctors", doctor_repo.findAll()); // saare doctors nikal lo
        return "Doctor-list"; // tumhare HTML page ka naam (doctors-list.html hona chahiye)
    }
    @GetMapping("/list-appointment")
    public String listAppointments(Model model) {
        model.addAttribute("appointments", appointment_repo.findAll());
        return "Appointment-list";// Tumhari HTML file ka exact naam
    }
    // Ye method status update karne ke liye hai
    @PostMapping("/appointments/update-status/{id}")
    public String updateStatus(@PathVariable("id") Long id, @RequestParam("status") String status) {
        Appointment_Entity appt = appointment_repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment nahi mili"));

        appt.setStatus(status); // Status update kiya
        appointment_repo.save(appt);    // Database mein save kiya

        return "redirect:/admin/list-appointment"; // List page par wapas bheja
    }
    // Patient List dikhane ke liye
    @GetMapping("/patient-list")
    public String listPatients(Model model) {
        List<Patient_Entity> patients = patient_repo.findAll();
        // Yahan check lagao
        if(patients == null) {
            patients = new ArrayList<>(); // Agar null ho toh empty list bhej do
        }
        model.addAttribute("patients", patient_repo.findAll());
        return "Patient-list";
    }
    @GetMapping("/download-receipt/{id}")
    public void downloadReceipt(@PathVariable Long id, HttpServletResponse response) throws Exception {
        // 1. Database se appointment fetch karo
        Appointment_Entity appointment = appointment_repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment nahi mili!"));

        // 2. Response setup karo
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=Receipt_" + id + ".pdf");

        // 3. PDF generate karo
        pdfService.generateAppointmentReceipt(appointment, response);
    }
}
