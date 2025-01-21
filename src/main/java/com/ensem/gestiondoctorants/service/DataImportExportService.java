package com.ensem.gestiondoctorants.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

@Service
public class DataImportExportService {

    public void processImport(MultipartFile file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {

            for (CSVRecord record : csvParser) {
                String column1 = record.get("Column1");
                String column2 = record.get("Column2");
                // Add your logic to process the data here
                System.out.println("Processing: " + column1 + ", " + column2);
            }
        }
    }
}
