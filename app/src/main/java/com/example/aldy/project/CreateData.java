package com.example.aldy.project;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aldy.project.db.Anime;
import com.example.aldy.project.db.DBDataSource;

public class CreateData extends Activity {
    Intent i = null;
    private Button buttonSubmit;
    private EditText edNamaAnime;
    private EditText edNamaGenre;
    private EditText edScore;
    private EditText edSumber;
    private DBDataSource dataSource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_data);

        buttonSubmit = (Button) findViewById(R.id.button_submit);
        edNamaAnime = (EditText) findViewById(R.id.edNamaAnime);
        edNamaGenre = (EditText) findViewById(R.id.edNamaGenre);
        edScore = (EditText) findViewById(R.id.edScore);
        edSumber = (EditText) findViewById(R.id.edSumber);
        dataSource = new DBDataSource(this);
        dataSource.open();
    }

    public void onCreateAnime(View v) {
        // Inisialisasi data barang
        String nama_anime = null;
        String nama_genre = null;
        String score = null;
        String sumber = null;
        @SuppressWarnings("unused")

        //inisialisasi barang baru (masih kosong)
        Anime anime = null;
        if(edNamaAnime.getText()!=null && edNamaGenre.getText()!=null&& edScore.getText()!=null&& edSumber.getText()!=null)
        {
            nama_anime = edNamaAnime.getText().toString();
            nama_genre = edNamaGenre.getText().toString();
            score = edScore.getText().toString();
            sumber = edSumber.getText().toString();
        }

        switch(v.getId())
        {
            case R.id.button_submit:
                anime = dataSource.createAnime(nama_anime, nama_genre, score, sumber);
                Toast.makeText(this,
                        "Anime " + anime.getNama_anime() +
                        " dengan genre " + anime.getNama_genre() + " berhasil di tambah" , Toast.LENGTH_LONG).show();
                break;
        }

    }

    public void to_viewdata(View v) {
        i=new Intent(this,ViewData.class);
        startActivityForResult(i, 500);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void to_creategenre(View v) {
        i=new Intent(this,CreateGenre.class);
        startActivityForResult(i, 500);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }


}
