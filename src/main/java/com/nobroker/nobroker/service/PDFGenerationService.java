package com.nobroker.nobroker.service;// PDFGenerationService.java
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.nobroker.nobroker.entity.User;
import com.nobroker.nobroker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
@Service
public class PDFGenerationService {

    @Autowired
    private UserRepository userRepository;

    public byte[] generatePDF() throws DocumentException {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);

            document.open();

            // Fetch data from the database
            List<User> users = userRepository.findAll();

            // Create a table with 5 columns (no need for an empty column)
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);

            // Add header row
            addTableHeader(table);

            // Add data rows
            for (User user : users) {
                addRow(table, user);
            }

            // Add the table to the document
            document.add(table);

            document.close();

            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addTableHeader(PdfPTable table) {
        table.addCell("ID");
        table.addCell("Name");
        table.addCell("Email");
        table.addCell("Mobile");
        table.addCell("Email Verified");
    }

    private void addRow(PdfPTable table, User user) {
        table.addCell(String.valueOf(user.getId()));
        table.addCell(user.getName());
        table.addCell(user.getEmail());
        table.addCell(user.getMobile());
        table.addCell(String.valueOf(user.isEmailVerified()));
    }
}


