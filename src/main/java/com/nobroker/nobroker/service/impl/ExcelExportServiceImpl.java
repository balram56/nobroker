package com.nobroker.nobroker.service.impl;
// ExcelExportServiceImpl.java


import com.nobroker.nobroker.entity.User;
import com.nobroker.nobroker.repository.UserRepository;
import com.nobroker.nobroker.service.ExcelExportService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
@Service
public class ExcelExportServiceImpl implements ExcelExportService {

    @Autowired
    private UserRepository userRepository;



    @Override
    public byte[] exportUsersToExcel() throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {

            List<User> users = userRepository.findAll();
            Sheet sheet = workbook.createSheet("Users");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Name");
            headerRow.createCell(2).setCellValue("Email");
            headerRow.createCell(3).setCellValue("Mobile");
            headerRow.createCell(4).setCellValue("Email Verified");

            // Populate data rows
            int rowNum = 1; // Second row in the Excel
            for (User user : users) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(user.getId());
                row.createCell(1).setCellValue(user.getName());
                row.createCell(2).setCellValue(user.getEmail());
                row.createCell(3).setCellValue(user.getMobile());
                row.createCell(4).setCellValue(user.isEmailVerified());
            }

            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                workbook.write(outputStream);
                return outputStream.toByteArray();
            }

        } catch (IOException e) {
            // Log the exception
            e.printStackTrace();
            throw e; // Re-throw the exception to be handled in the controller
        }
    }
}


