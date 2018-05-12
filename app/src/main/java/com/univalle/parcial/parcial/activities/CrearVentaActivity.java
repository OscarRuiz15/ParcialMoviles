package com.univalle.parcial.parcial.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;

import com.univalle.parcial.parcial.R;
import com.univalle.parcial.parcial.conexion.ProductoBD;
import com.univalle.parcial.parcial.modelo.Producto;
import com.univalle.parcial.parcial.modelo.Venta;

import java.util.ArrayList;
import java.util.List;

public class CrearVentaActivity extends AppCompatActivity {

    private Spinner spinner;
    
    private EditText txtentrega;
    private EditText txtcantidad;
    private ArrayList<Integer>ids;
    private ArrayList<String>nombres;
    private ArrayList<Integer>cantidades;
    private ArrayList<Integer>precio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_venta);

        spinner = (Spinner) findViewById(R.id.spinneropcion);

        txtentrega=(EditText) findViewById(R.id.txtdateentrega);
        txtcantidad=(EditText) findViewById(R.id.txtcantidadvente);
        ProductoBD pbd = new ProductoBD(this, "parcial", null, 1);
        List<Producto> productos = new ArrayList<>();/*pbd.consultarProductos();*/
        Producto p = new Producto(1, "Minutos", 300);
        productos.add(p);
        p = new Producto(2, "modem", 1500);
        productos.add(p);
        String lista[] = new String[productos.size()];
        System.out.println(lista.length);
        for (int i = 0; i < lista.length; i++) {
            lista[i] = productos.get(i).getItem();

        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinner.getSelectedItemPosition() == 0) {
                   txtcantidad.setVisibility(View.VISIBLE);

                    txtentrega.setVisibility(View.GONE);
                } else if (spinner.getSelectedItemPosition() == 1) {

                    txtentrega.setVisibility(View.VISIBLE);
                    txtcantidad.setVisibility(View.GONE);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });
    }

    public void crearVenta(View v){
        ProductoBD pbd=new ProductoBD(this,"Parcial",null,1);

        int id=spinner.getSelectedItemPosition();
        String nombre=(String)spinner.getSelectedItem();

        Intent intent=new Intent(CrearVentaActivity.this,MainActivity.class);
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_CLEAR_TASK);
        Bundle bundle=new Bundle();
        bundle.putIntegerArrayList("ids",ids);
        bundle.putIntegerArrayList("cantidades",cantidades);
        bundle.putStringArrayList("nombres",nombres);
        bundle.putIntegerArrayList("precio",precio);
        startActivity(intent);
    }



}
