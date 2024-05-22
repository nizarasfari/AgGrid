package com.example.server;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    public static List<String> listFiles(){
        File dir = new File("files");
        File[] files = dir.listFiles();
        List<String> fileNames = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    fileNames.add(file.getName());
                }
            }
        }

        return fileNames;
    }
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