package com.example.aldy.project;

import java.util.ArrayList;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.app.Dialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.example.aldy.project.db.Anime;
import com.example.aldy.project.db.DBDataSource;

public class ViewData extends ListActivity implements OnItemLongClickListener {

    private DBDataSource dataSource;

    //inisialisasi arraylist
    private ArrayList<Anime> values;
    private Button editButton;
    private Button delButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_data);

        dataSource = new DBDataSource(this);
        dataSource.open();
        values = dataSource.getAllAnime();
        ArrayAdapter<Anime> adapter = new ArrayAdapter<Anime>(this,
                android.R.layout.simple_list_item_1, values);

        // set adapter pada list
        setListAdapter(adapter);

        ListView lv = (ListView) findViewById(android.R.id.list);
        lv.setOnItemLongClickListener(this);
    }

    @Override
    public boolean onItemLongClick(final AdapterView<?> adapter, View v, int pos, final long id) {

        //tampilkan alert dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_anime);
        dialog.setTitle("Pilih Aksi");
        dialog.show();
        final Anime b = (Anime) getListAdapter().getItem(pos);
        editButton = (Button) dialog.findViewById(R.id.button_edit_anime);
        delButton = (Button) dialog.findViewById(R.id.button_del_anime);

        //apabila tombol edit diklik
        editButton.setOnClickListener(
                new OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        switchToEdit(b.getId_anime());
                        dialog.dismiss();
                    }
                }
        );
        //apabila tombol delete di klik
        delButton.setOnClickListener(
                new OnClickListener()
                {
                    @Override
                    public void onClick(View v) {

                        dataSource.deleteAnime(b.getId_anime());
                        dialog.dismiss();
                        finish();
                        startActivity(getIntent());
                    }
                }
        );
        return true;
    }
    //method untuk edit data
    public void switchToEdit(long id_anime)
    {
        Anime b = dataSource.getAnime(id_anime);
        Intent i = new Intent(this, EditData.class);
        Bundle bun = new Bundle();
        bun.putLong("id_anime", b.getId_anime());
        bun.putString("nama_anime", b.getNama_anime());
        bun.putString("nama_genre", b.getNama_genre());
        bun.putString("score", b.getScore());
        bun.putString("sumber", b.getSumber());
        i.putExtras(bun);
        finale();
        startActivity(i);
    }
    //method yang dipanggil ketika edit data selesai
    public void finale()
    {
        ViewData.this.finish();
        dataSource.close();
    }
    @Override
    protected void onResume() {
        dataSource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        dataSource.close();
        super.onPause();
    }
}