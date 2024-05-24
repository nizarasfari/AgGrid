package com.example.server;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.server.PersonClass;


import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class PersonService {
    public static List<PersonClass> listPersons(int page, int pageSize){
        //get the json file and read it
        String jsonFile = "/Users/wrok/Desktop/AgGrid/server/src/main/java/com/example/server/data.json";
        ObjectMapper objectMapper = new ObjectMapper();
        List <PersonClass> data = new ArrayList<>();


        try{
            data = objectMapper.readValue(new File(jsonFile),  new TypeReference<List<PersonClass>>() {});
        }catch (IOException e){
            System.out.println("Could not read JSON file.");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        int start = page * pageSize;
        int end= Math.min(start + pageSize, data.size());
        List<PersonClass> people = new ArrayList();

        for (int i = start; i < end; i++) {
            people.add(data.get(i));
        }


        return  people;


    }
}