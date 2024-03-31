package com.nobroker.nobroker.controller;

// UserController.java

import com.nobroker.nobroker.service.ExcelExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
@RestController
@RequestMapping("/api")
public class ExcelController {

    @Autowired
    private ExcelExportService excelExportService;

    // http://localhost:8080/api/export-to-excel
    @GetMapping("/export-to-excel")
    public ResponseEntity<?> exportToExcel() {
        try {
            byte[] excelBytes = excelExportService.exportUsersToExcel();
            String fileName = "users.xlsx";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData(fileName, fileName);

            return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error exporting Excel: " + e.getMessage());
        }
    }
}



