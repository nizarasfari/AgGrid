package com.example.server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.example.server.PersonService;
import com.example.server.PersonClass;


import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;


class PersonPage {
    private List<PersonClass> person;
    private int totalPersons;

    public PersonPage(List<PersonClass> person, int totalPersons) {
        this.person = person;
        this.totalPersons = totalPersons;
    }

    public List<PersonClass> getPerson() {
        return person;
    }

    public int getTotalPersons() {
        return totalPersons;
    }
}

@RestController
public class HomeController {
    @CrossOrigin(origins = "http://localhost:4200")


    @GetMapping("/list/persons")
    @ResponseBody
    public ResponseEntity<PersonPage> listPersons(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        try {
            List<PersonClass> personList = PersonService.listPersons(page, size);
            int totalRows = 500;
            PersonPage personPage = new PersonPage(personList,totalRows);
            return  ResponseEntity.ok(personPage);
        } catch (Exception e) {
            e.printStackTrace();

            System.out.println("Error occurred: " + e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "Hello, World!";
    }
}
