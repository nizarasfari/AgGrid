package com.example.server;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class FilesService {
    public static void  createFile(String fileName){
        File dir = new File("files");

        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(dir + File.separator + fileName);
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
                throw new IOException("File already exists.");
            }

        }catch (IOException e){
            System.out.println("File could not be created.");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public static List<String> listFiles(int page, int size ){
        Path dir = Paths.get("files");
        if(!Files.exists(dir)){
           return  new ArrayList<>();
        }

        //lazy stream items are not read in memomry all at once
        try (Stream<Path> stream = Files.list(dir)) {
            List<String> fileNames = new ArrayList<>();
            stream.skip(page) // this  gives a dyniacm approch so we sit a window size from 0-10 for example
                    .limit(size) // max size of items we would like to store in memmor
                    .filter(Files::isRegularFile)
                    .forEach(path -> fileNames.add(path.getFileName().toString()));
            return fileNames;
        }catch (IOException e){
            System.out.println("File could not be created.");
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }

//
//    public static List<Person> listStrings(int page, int size) {
//
//        String jsonFile = "files/data.json";
//        ObjectMapper objectMapper = new ObjectMapper();
//        List <Person> persons = new ArrayList<>();
//
//        try{
//            persons = objectMapper.readValue(new File(jsonFile),  new TypeReference<List<Person>>() {});
//        }catch (IOException e){
//            System.out.println("Could not read JSON file.");
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//
//        List<Person> paginatedPeople = new ArrayList<>();
//        int start = page * size;
//        int end = Math.min(start + size, persons.size());
//
//        for (int i = start; i < end; i++) {
//            paginatedPeople.add(persons.get(i));
//        }
//
//        return paginatedPeople;
//    }

//
//    public static void deleteFile(String fileName){
//        File file = new File("files/" + fileName);
//        if (file.exists()) {
//            file.delete();
//            return;
//        }
//
//    }
//
//    public static editFile(String fileName){
//        File file = new File("files/" + fileName);
//        if (file.exists()) {
//            file.replace(fileName ()=> fileName = fileName)
//        }
//    }


}