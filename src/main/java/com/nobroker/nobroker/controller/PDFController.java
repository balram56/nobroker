package com.nobroker.nobroker.controller;
// PDFController.java
import com.itextpdf.text.DocumentException;
import com.nobroker.nobroker.service.PDFGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PDFController {

    @Autowired
    private PDFGenerationService pdfGenerationService;

    //http://localhost:8080/api/generate-pdf
    @GetMapping("/generate-pdf")
    public ResponseEntity<byte[]> generatePDF() {
        try {
            byte[] pdfBytes = pdfGenerationService.generatePDF();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "sample.pdf");

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (DocumentException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(("Error generating PDF: " + e.getMessage().getBytes()).getBytes());
        }
    }
}

