package com.nobroker.nobroker.service;

// ExcelExportService.java
import java.io.IOException;

public interface ExcelExportService {
    byte[] exportUsersToExcel() throws IOException;
}

