package com.univalle.parcial.parcial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.univalle.parcial.parcial.conexion.ProductoBD;
import com.univalle.parcial.parcial.modelo.Producto;

import java.util.List;

public class CrearVentaActivity extends AppCompatActivity {

    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spinner=(Spinner)findViewById(R.id.spinneropcion);
        ProductoBD pbd=new ProductoBD(this,"parcial",null,1);
        List<Producto> productos=pbd.consultarProductos();
        String lista[]=new String[productos.size()];
        for (int i = 0; i <lista.length ; i++) {
            lista[i]=productos.get(i).getItem();
        }
        ArrayAdapter adapter=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,lista);
        spinner.setAdapter(adapter);
        setContentView(R.layout.activity_crear_venta);
    }
}
