package com.univalle.parcial.parcial.conexion;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.univalle.parcial.parcial.modelo.Cliente;
import com.univalle.parcial.parcial.modelo.Venta;

import java.text.SimpleDateFormat;
import java.util.List;

public class VentaBD extends ConexionBD {
    private SQLiteDatabase db;
    public VentaBD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        db=super.getWritableDatabase();
    }

    public boolean insertarVenta(Venta v){

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        String fecha=sdf.format(v.getFecha());

        ContentValues registro = new ContentValues();

        registro.put("idcliente", v.getCliente().getId());
        registro.put("idproducto", v.getProducto().getId());
        registro.put("fecha", fecha);
        registro.put("cantidad", v.getCantidad());
        registro.put("preciounitario", v.getProducto().getPrecio());
        registro.put("total", v.getTotal());

        db.insert("cliente", null, registro);
        db.close();
        return true;
    }


    public List<Venta> consultarVentaPorCliente(Cliente c){

        return null;
    }
    public List<Venta> consultarVentas(){

        return null;

    }
}
