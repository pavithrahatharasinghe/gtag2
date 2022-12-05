package com.example.gtag2.Models;


public class CurrentMp3File {

    String filepath;
    String filename;

    public CurrentMp3File(String filepath, String filename) {
        this.filepath = filepath;
        this.filename = filename;
    }

    public CurrentMp3File() {

    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
