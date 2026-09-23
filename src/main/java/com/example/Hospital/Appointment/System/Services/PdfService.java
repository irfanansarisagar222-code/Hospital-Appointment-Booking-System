package com.example.Hospital.Appointment.System.Services;

import com.example.Hospital.Appointment.System.Appointment.Appointment_Entity;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public class PdfService {
    public void generateAppointmentReceipt(Appointment_Entity appointment, HttpServletResponse response) throws Exception {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        // Header Title
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22);
        Paragraph title = new Paragraph("Hospital Appointment Report", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(new Paragraph("\n"));

        // Table setup
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        // Rows add karna
        addTableRow(table, "Patient Name:", appointment.getPatient().getName());
        addTableRow(table, "Doctor Name:", appointment.getDoctor().getName());
        addTableRow(table, "Specialization:", appointment.getDoctor().getSpecialization());
        addTableRow(table, "Appointment Date:", appointment.getAppointmentDate().toString());
        addTableRow(table, "Consultation Fee:", "Rs. " + appointment.getDoctor().getConsultationFee());
        addTableRow(table, "Health Issue/Symptoms:", appointment.getSymptoms());

        document.add(table);

        document.add(new Paragraph("\n\n"));
        document.add(new Paragraph("Note: Please bring this report during your visit.",
                FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 10)));

        document.close();
    }

    private void addTableRow(PdfPTable table, String field, String value) {
        table.addCell(new Phrase(field, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
        table.addCell(new Phrase(value != null ? value : "N/A", FontFactory.getFont(FontFactory.HELVETICA, 12)));
    }
}
