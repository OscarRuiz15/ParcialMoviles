package com.univalle.parcial.parcial.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.univalle.parcial.parcial.R;
import com.univalle.parcial.parcial.conexion.ConexionBD;
import com.univalle.parcial.parcial.conexion.ProductoBD;
import com.univalle.parcial.parcial.conexion.UsuarioBD;
import com.univalle.parcial.parcial.modelo.Producto;
import com.univalle.parcial.parcial.modelo.Usuario;

public class ActivityLogin extends AppCompatActivity {

    private EditText txtusername, txtpassword;
    ConexionBD conexion;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtusername = (EditText) findViewById(R.id.txtusername);
        txtpassword = (EditText) findViewById(R.id.txtpassword);

        //txtlogin.setText("admin");
        //txtpassword.setText("admin");

        conexion = new ConexionBD(this, "Parcial", null, 1);

        String DB_PATH = "/data/data/com.univalle.parcial.parcial/databases/Parcial";
        try {
            db = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READONLY);
            db.close();
            Toast.makeText(getApplication(), "Ya existe la BD", Toast.LENGTH_LONG).show();
        } catch (SQLiteException e) {
            //Si no existe la BD
            db = conexion.getWritableDatabase();
            if (conexion != null) {
                Toast.makeText(getApplication(), "BD creada", Toast.LENGTH_LONG).show();
                String query;
                UsuarioBD u = new UsuarioBD(this, "Parcial", null, 1);
                String pass = u.md5("admin");
                query = "insert into usuario (id , username, password) values (0,'admin','" + pass + "');";
                db.execSQL(query);
            }
        }
    }

    public void ingresarSistema(View view) {
        Intent intent=new Intent(ActivityLogin.this,MainActivity.class);
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_CLEAR_TASK);
        Bundle bundle=new Bundle();
        bundle.putInt("id",1);

        bundle.putString("nombre","Juan");
        bundle.putString("email","Email@e-mail.com");
        bundle.putInt("tipo",0);
        intent.putExtras(bundle);
        startActivity(intent);
        /*String username = txtusername.getText().toString().trim();
        String pass = txtpassword.getText().toString().trim();


        if (username.equals("") || pass.equals("")) {
            String message = "Hay campos vacios";
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setMessage(message);
            alertDialog.show();
        } else {
            UsuarioBD u = new UsuarioBD(this, "Parcial", null, 1);
            Usuario us = u.verificarID(username, pass);
            if (us != null) {
                    Intent intent = new Intent(ActivityLogin.this, MainActivity.class);
                    intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_CLEAR_TASK);
                    *//*Bundle bundle = new Bundle();
                    bundle.putInt("id", us.getId());
                    bundle.putString("nombre", us.getNombre());
                    bundle.putString("email", us.getEmail());
                    bundle.putInt("tipo", us.getTipo());
                    intent.putExtras(bundle);*//*
                    startActivity(intent);
                Toast.makeText(getApplication(), "Se logea papu", Toast.LENGTH_LONG).show();
            } else {
                String message = "Usuario erroneo";
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setMessage(message);
                alertDialog.show();
                txtusername.setText("");
                txtpassword.setText("");
            }
        }
    }*/
    }
}
