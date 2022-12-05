package com.example.gtag2.Models;

public class mp3file {
    String fileName;
    String filePath;
    String Title;
    String artist;
    String Album;
    String Year;
    String genre;


    public mp3file(String fileName, String filePath, String title, String artist, String album, String year, String genre) {
        this.fileName = fileName;
        this.filePath = filePath;
        Title = title;
        this.artist = artist;
        Album = album;
        Year = year;
        this.genre = genre;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String album) {
        Album = album;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
