package com.univalle.parcial.parcial.conexion;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.univalle.parcial.parcial.modelo.Cliente;

public class ClienteBD extends ConexionBD {
    private SQLiteDatabase db;

    public ClienteBD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        db=super.getWritableDatabase();
    }

    public boolean insertarCliente(Cliente c){
        ContentValues registro = new ContentValues();
        registro.put("id",c.getId());
        registro.put("nombre", c.getNombre());
        registro.put("apellido", c.getApellido());
        registro.put("email", c.getEmail());

        db.insert("cliente", null, registro);
        db.close();

        return true;
    }

    public boolean modificarCliente(Cliente c){
        ContentValues registro = new ContentValues();
        registro.put("nombre", c.getNombre());
        registro.put("apellido", c.getApellido());
        registro.put("email", c.getEmail());

        db.update("cliente", registro, "codigo=" + c.getId(), null);
        db.close();
        return true;


    }



}
