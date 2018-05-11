package com.univalle.parcial.parcial.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.univalle.parcial.parcial.R;
import com.univalle.parcial.parcial.conexion.ProductoBD;
import com.univalle.parcial.parcial.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public class CrearVentaActivity extends AppCompatActivity {

    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_venta);

        spinner=(Spinner)findViewById(R.id.spinneropcion);
        ProductoBD pbd=new ProductoBD(this,"parcial",null,1);
        List<Producto> productos=new ArrayList<>();/*pbd.consultarProductos();*/
        Producto p=new Producto(1,"Minutos",300);
        productos.add(p);
        p=new Producto(2,"modem",1500);
        productos.add(p);
        String lista[]=new String[productos.size()];
        System.out.println(lista.length);
        for (int i = 0; i <lista.length ; i++) {
            lista[i]=productos.get(i).getItem();

        }
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


    }
}
