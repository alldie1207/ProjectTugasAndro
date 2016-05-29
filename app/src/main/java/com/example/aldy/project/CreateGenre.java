package com.example.aldy.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aldy.project.db.Anime;
import com.example.aldy.project.db.DBDataSource;
import com.example.aldy.project.db.Genre;

public class CreateGenre extends AppCompatActivity {

    private Button buttonSaveGenre;
    private EditText edNamaGenre;
    private DBDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_genre);

        buttonSaveGenre = (Button) findViewById(R.id.button_submit);
        edNamaGenre = (EditText) findViewById(R.id.edNamaGenre);
        dataSource = new DBDataSource(this);
        dataSource.open();
    }

    public void onCreateGenre(View v) {
        // Inisialisasi data barang
        String nama_genre = null;
        @SuppressWarnings("unused")

        //inisialisasi barang baru (masih kosong)
                Genre genre = null;
        if(edNamaGenre.getText()!=null)
        {
            nama_genre = edNamaGenre.getText().toString();
        }

        switch(v.getId())
        {
            case R.id.btnSaveGenre:
                genre = dataSource.createGenre(nama_genre);
                Toast.makeText(this,
                        genre.getNama_genre()  + " berhasil di tambah" , Toast.LENGTH_LONG).show();
                break;
        }

    }
}
