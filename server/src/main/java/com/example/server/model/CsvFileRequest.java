package com.yourcompany.yourproject.model;

import java.util.List;

public class CsvFileRequest {
    private String filename;
    private List<String[]> data;

    // Getters and Setters
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public List<String[]> getData() {
        return data;
    }

    public void setData(List<String[]> data) {
        this.data = data;
    }
}
