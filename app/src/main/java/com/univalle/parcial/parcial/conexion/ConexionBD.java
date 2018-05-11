package com.univalle.parcial.parcial.conexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionBD extends SQLiteOpenHelper {

    String query="create table cliente (id integer primary key autoincrement, nombre varchar, apellido varchar, email varchar);";
    String query2 ="create table producto (id integer primary key autoincrement, item varchar, precio varchar);";
    String query3 ="create table venta (id integer primary key autoincrement, idcliente integer, idproducto integer, fecha datetime, cantidad integer, preciounitario integer, total integer);";
    String query4 ="create table usuario (id integer primary key autoincrement, username varchar, password varchar);";

    public ConexionBD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
