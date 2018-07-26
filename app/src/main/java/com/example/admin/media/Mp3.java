package com.example.admin.media;

public class Mp3 {
    private String name;
    private String artist;
    private int id;

    public Mp3(String name, String artist, int id) {
        this.name = name;
        this.artist = artist;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
