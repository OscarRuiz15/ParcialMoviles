package com.univalle.parcial.parcial.conexion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.univalle.parcial.parcial.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductoBD extends ConexionBD {

    private SQLiteDatabase db;
    public ProductoBD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        db=super.getWritableDatabase();
    }

    public boolean insertarProducto(Producto p){

        ContentValues registro = new ContentValues();
        registro.put("id",p.getId());
        registro.put("item", p.getItem());
        registro.put("precio", p.getPrecio());


        db.insert("producto", null, registro);
        db.close();

        return true;

    }

    public boolean modificarProducto(Producto p){

        ContentValues registro = new ContentValues();
        registro.put("id",p.getId());
        registro.put("item", p.getItem());
        registro.put("precio", p.getPrecio());
        db.update("producto", registro, "id=" + p.getId(), null);
        db.close();
        return true;
    }

    public List<Producto> consultarProductos(){
        List<Producto> productos = new ArrayList<>();
        Producto p = null;
        String query = "select * from producto";
        Cursor fila = db.rawQuery(query, null);
        if (fila.moveToFirst()) {
            do {
                int id = fila.getInt(0);
                String item = fila.getString(1);
                int precio = fila.getInt(2);

                p = new Producto(id,item,precio);
                productos.add(p);
            } while (fila.moveToNext());
        }
        fila.close();

        return productos;
    }
}
