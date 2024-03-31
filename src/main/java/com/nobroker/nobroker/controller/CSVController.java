package com.nobroker.nobroker.controller;

import com.nobroker.nobroker.entity.User;
import com.nobroker.nobroker.repository.UserRepository;
import com.nobroker.nobroker.service.CSVGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/csv")
public class CSVController {

    @Autowired
    private UserRepository userRepository; // Assuming you have a UserRepository

    @Autowired
    private CSVGenerationService csvGenerationService;

    //http://localhost:8080/api/csv/users
    @GetMapping("/users")
    public ResponseEntity<byte[]> generateCSV() throws IOException {
        List<User> users = userRepository.findAll();
        String csvContent = csvGenerationService.generateCSV(users);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=users.csv");

        return ResponseEntity.ok()
                .headers(headers)
                .body(csvContent.getBytes());
    }
}
