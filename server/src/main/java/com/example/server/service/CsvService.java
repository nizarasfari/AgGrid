package com.yourcompany.yourproject.service;

import com.yourcompany.yourproject.model.CsvFileRequest;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvService {

    private static final String CSV_DIRECTORY = "csv_files/";

    public List<String> getCsvFiles() {
        File directory = new File(CSV_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        String[] files = directory.list((dir, name) -> name.endsWith(".csv"));
        return files != null ? List.of(files) : new ArrayList<>();
    }

    public List<String[]> readCsvFile(String filename) {
        try {
            List<String[]> lines = new ArrayList<>();
            List<String> fileLines = Files.readAllLines(Paths.get(CSV_DIRECTORY + filename));
            for (String line : fileLines) {
                lines.add(line.split(","));
            }
            return lines;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void createCsvFile(CsvFileRequest request) {
        try {
            File file = new File(CSV_DIRECTORY + request.getFilename());
            if (!file.exists()) {
                file.createNewFile();
            }
            StringBuilder data = new StringBuilder();
            for (String[] line : request.getData()) {
                data.append(String.join(",", line)).append("\n");
            }
            FileUtils.writeStringToFile(file, data.toString(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateCsvFile(CsvFileRequest request) {
        createCsvFile(request);
    }

    public void deleteCsvFile(String filename) {
        try {
            Files.deleteIfExists(Paths.get(CSV_DIRECTORY + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
