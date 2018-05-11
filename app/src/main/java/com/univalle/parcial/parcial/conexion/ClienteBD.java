package com.univalle.parcial.parcial.conexion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.univalle.parcial.parcial.modelo.Cliente;

import java.util.ArrayList;
import java.util.List;

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
        registro.put("tipo", c.getApellido());
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

    public List<Cliente> consultarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        Cliente u = null;
        String query = "select * from Cliente";
        Cursor fila = db.rawQuery(query, null);
        if (fila.moveToFirst()) {
            do {
                int id = fila.getInt(0);
                String nombre = fila.getString(1);
                String apellido = fila.getString(2);
                String email = fila.getString(3);
                u = new Cliente(id, nombre, apellido, email);
                clientes.add(u);
            } while (fila.moveToNext());
        }
        fila.close();

        return clientes;

    }


}
