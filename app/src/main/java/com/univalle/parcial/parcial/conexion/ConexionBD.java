package com.univalle.parcial.parcial.conexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionBD extends SQLiteOpenHelper {

    String query="create table cliente (id integer primary key autoincrement, nombre varchar, apellido varchar, email varchar);";
    String query2 ="create table producto (id integer primary key autoincrement, item varchar, precio integer);";
    String query3 ="create table venta (id integer primary key autoincrement, idcliente integer, idproducto integer, fecha varchar, cantidad integer, total integer);";
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

        db.execSQL("insert into cliente (id,nombre,apellido,email) values (1,'Oscar','Ruiz','oarp1996@hotmail.com');");
        db.execSQL("insert into producto (id,item,precio) values (1,'Arroz',1500);");
        db.execSQL("insert into producto (id,item,precio) values (2,'Papa',600);");
        db.execSQL("insert into producto (id,item,precio) values (3,'Cerveza',2200);");
        db.execSQL("insert into venta (id,idcliente,idproducto,fecha,cantidad,total) values (1,1,1,'11052018',2,3000);");
        db.execSQL("insert into venta (id,idcliente,idproducto,fecha,cantidad,total) values (2,1,2,'11052018',3,1800);");
        db.execSQL("insert into venta (id,idcliente,idproducto,fecha,cantidad,total) values (3,1,3,'11052018',1,2200);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
