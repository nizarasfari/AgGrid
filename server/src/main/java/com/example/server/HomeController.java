package com.example.server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.example.server.FilesService;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/createFile")
    public ResponseEntity<String> createFile(@RequestParam String fileName, @RequestParam  String fileExtension ){
        String file = fileName+"."+fileExtension;
        try {
            FilesService.createFile(file);
            return  ResponseEntity.ok("file created" + file);

        }catch (Exception e){
            if (e.getMessage().contains("File already exists")) {
                return ResponseEntity.status(409).body("File already exists.");
            } else {
                return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
            }
        }
    }

    @GetMapping("/list/files")
    public ResponseEntity<List<String>> listFiles() {
        try {
            List<String> fileNames = FilesService.listFiles();
            return ResponseEntity.ok(fileNames);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }



    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "Hello, World!";
    }
}
