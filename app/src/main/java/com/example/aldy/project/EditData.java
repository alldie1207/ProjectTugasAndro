package com.example.aldy.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aldy.project.db.Anime;
import com.example.aldy.project.db.DBDataSource;

public class EditData extends Activity implements OnClickListener{

    private DBDataSource dataSource;

    private long id_anime;
    private String nama_anime;
    private String nama_genre;

    private EditText edNamaAnime;
    private EditText edNamaGenre;

    private TextView txId;

    private Button btnSave;
    private Button btnCancel;

    private Anime anime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data);
        //inisialisasi variabel
        edNamaAnime = (EditText) findViewById(R.id.editText_nama);
        edNamaGenre = (EditText) findViewById(R.id.editText_genre);
        txId = (TextView) findViewById(R.id.id_anime);
        //buat sambungan baru ke database
        dataSource = new DBDataSource(this);
        dataSource.open();

        Bundle bun = this.getIntent().getExtras();
        id_anime = bun.getLong("id_anime");
        nama_anime = bun.getString("nama_anime");
        nama_genre = bun.getString("nama_genre");

        txId.append(String.valueOf(id_anime));
        edNamaAnime.setText(nama_anime);
        edNamaGenre.setText(nama_genre);

        //set listener pada tombol
        btnSave = (Button) findViewById(R.id.button_save_update);
        btnSave.setOnClickListener(this);
        btnCancel = (Button) findViewById(R.id.button_cancel_update);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId())
        {
            // apabila tombol save diklik (update barang)
            case R.id.button_save_update :
                anime = new Anime();
                anime.setNama_anime(edNamaAnime.getText().toString());
                anime.setNama_genre(edNamaGenre.getText().toString());
                anime.setId_anime(id_anime);
                dataSource.updateAnime(anime);
                Intent i = new Intent(this, ViewData.class);
                startActivity(i);
                EditData.this.finish();
                dataSource.close();
                break;
            // apabila tombol cancel diklik, finish activity
            case R.id.button_cancel_update :
                finish();
                dataSource.close();
                break;
        }
    }
}
