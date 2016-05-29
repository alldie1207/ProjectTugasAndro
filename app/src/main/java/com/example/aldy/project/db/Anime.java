package com.example.aldy.project.db;

/**
 * Created by Aldy on 5/24/2016.
 */
public class Anime {

    private long id_anime;
    private String nama_anime;
    private String nama_genre;
    private String score;
    private String sumber;

    public Anime() { }

    public long getId_anime() {
        return id_anime;
    }

    public void setId_anime(long id_anime) {
        this.id_anime = id_anime;
    }

    public String getNama_anime() {
        return nama_anime;
    }

    public void setNama_anime(String nama_anime) {
        this.nama_anime = nama_anime;
    }

    public String getNama_genre() {
        return nama_genre;
    }

    public void setNama_genre(String nama_genre) {
        this.nama_genre = nama_genre;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getSumber() {
        return sumber;
    }

    public void setSumber(String sumber) {
        this.sumber = sumber;
    }

    @Override
    public String toString()
    {
        return "Anime : " +nama_anime +" genre : "+ nama_genre+"\n score : "+ score+" sumber : "+ sumber ;
    }
}
