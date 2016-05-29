package com.example.aldy.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aldy.project.db.DBDataSource;
import com.example.aldy.project.db.User;

public class Signin extends Activity  {

    Intent i = new Intent();
    private Button buttonLogin;
    private Button buttonSignup;
    private EditText edNama;
    private EditText edAlamat;
    private EditText edEmail;
    private EditText edUsername;
    private EditText edPassword;

    private DBDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        buttonSignup = (Button) findViewById(R.id.signup);
        edNama = (EditText) findViewById(R.id.nama);
        edAlamat = (EditText) findViewById(R.id.alamat);
        edEmail = (EditText) findViewById(R.id.email);
        edUsername = (EditText) findViewById(R.id.username);
        edPassword = (EditText) findViewById(R.id.password);
        dataSource = new DBDataSource(this);
        dataSource.open();
    }

    public void onClick(View v) {

        String username = null;
        String password = null;
        String nama = null;
        String alamat = null;
        String email = null;
        @SuppressWarnings("unused")

        User user = null;
        if(edUsername.getText()!=null && edPassword.getText()!=null&& edNama.getText()!=null&& edAlamat.getText()!=null&& edEmail.getText()!=null)
        {
            username = edUsername.getText().toString();
            password = edPassword.getText().toString();
            nama = edNama.getText().toString();
            alamat = edAlamat.getText().toString();
            email = edEmail.getText().toString();
        }

        switch(v.getId())
        {
            case R.id.signup:

                user = dataSource.createUser(username, password, nama, alamat, email);

                Toast.makeText(this, "Silahkan Login \n" +
                        "pemilik akun dengan user " + user.getUsername() + " berhasil registrasi" , Toast.LENGTH_LONG).show();
                break;
        }

    }

    public void SignIn(View v) {
        i=new Intent(this,Login.class);
        startActivityForResult(i, 500);
        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_top);
    }
}