package com.nobroker.nobroker.service;

import com.nobroker.nobroker.entity.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

@Service
public class CSVGenerationService {

    public String generateCSV(List<User> users) throws IOException {
        StringWriter sw = new StringWriter();
        CSVPrinter csvPrinter = new CSVPrinter(sw, CSVFormat.DEFAULT
                .withHeader("ID", "Name", "Email", "Mobile", "Password", "Email Verified"));

        for (User user : users) {
            csvPrinter.printRecord(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getMobile(),
                    user.getPassword(),
                    user.isEmailVerified()
            );
        }

        csvPrinter.flush();
        return sw.toString();
    }
}
