package com.univalle.parcial.parcial.conexion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.univalle.parcial.parcial.activities.VerVentasActivity;
import com.univalle.parcial.parcial.modelo.Cliente;
import com.univalle.parcial.parcial.modelo.Producto;
import com.univalle.parcial.parcial.modelo.Venta;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    public List<Venta> consultarVentas(VerVentasActivity verVentasActivity){
        List<Venta> ventas = new ArrayList<>();
        Venta vd = null;
        String query = "select * from venta";
        Cursor fila = db.rawQuery(query, null);
        Cliente cliente;
        if (fila.moveToFirst()) {
            do {
                int id = fila.getInt(0);
                int idcliente = fila.getInt(1);
                int idproducto = fila.getInt(2);
                String fecha = fila.getString(3);
                int cantidad=fila.getInt(4);
                int total=fila.getInt(5);

                ClienteBD cbd=new ClienteBD(verVentasActivity, "Parcial", null, 1);
                Cliente client=cbd.consultarId(idcliente);

                ProductoBD pbd=new ProductoBD(verVentasActivity, "Parcial", null, 1);
                Producto product=pbd.consultarId(idproducto);

                vd = new Venta(id, client, product, fecha, cantidad, total);
                ventas.add(vd);
            } while (fila.moveToNext());
        }
        fila.close();

        return ventas;

    }
}
