package com.example.aldy.project.db;

/**
 * Created by Aldy on 5/24/2016.
 */
public class Genre {

    private long id_genre;
    private String nama_genre;

    public Genre()
    {

    }

    public long getId_genre() {
        return id_genre;
    }

    public void setId_genre(long id_genre) {
        this.id_genre = id_genre;
    }

    public String getNama_genre() {
        return nama_genre;
    }

    public void setNama_genre(String nama_genre) {
        this.nama_genre = nama_genre;
    }

    @Override
    public String toString()
    {
        return "Jenis "+ nama_genre + "Telah diinput";
    }
}
