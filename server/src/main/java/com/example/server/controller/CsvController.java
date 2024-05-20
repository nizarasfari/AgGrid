package com.yourcompany.yourproject.controller;

import com.yourcompany.yourproject.model.CsvFileRequest;
import com.yourcompany.yourproject.service.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/csv")// this our endpoint
public class CsvController {

    @Autowired //what's the point ??
    private CsvService csvService;

    @GetMapping("/csv_files")
    public ResponseEntity<List<String>> getCsvFiles() {
        return ResponseEntity.ok(csvService.getCsvFiles());
    }

    @GetMapping("/file/{filename}")
    public ResponseEntity<List<String[]>> readCsvFile(@PathVariable String filename) {
        return ResponseEntity.ok(csvService.readCsvFile(filename));
    }

    @PostMapping("/file")
    public ResponseEntity<Void> createCsvFile(@RequestBody CsvFileRequest request) {
        csvService.createCsvFile(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/file")
    public ResponseEntity<Void> updateCsvFile(@RequestBody CsvFileRequest request) {
        csvService.updateCsvFile(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/file/{filename}")
    public ResponseEntity<Void> deleteCsvFile(@PathVariable String filename) {
        csvService.deleteCsvFile(filename);
        return ResponseEntity.ok().build();
    }
}
