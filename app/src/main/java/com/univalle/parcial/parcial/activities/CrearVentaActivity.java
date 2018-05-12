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
import android.widget.Toast;

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
        if (getIntent().getExtras()!=null) {
            ids = getIntent().getExtras().getIntegerArrayList("ids");
            nombres = getIntent().getExtras().getStringArrayList("nombres");
            cantidades = getIntent().getExtras().getIntegerArrayList("cantidades");
            precio = getIntent().getExtras().getIntegerArrayList("precio");
        }

    else{
        ids=new ArrayList<>();
        nombres=new ArrayList<>();
        cantidades=new ArrayList<>();
        precio=new ArrayList<>();
    }
        spinner = (Spinner) findViewById(R.id.spinneropcion);

        txtentrega=(EditText) findViewById(R.id.txtdateentrega);
        txtcantidad=(EditText) findViewById(R.id.txtcantidadvente);
        ProductoBD pbd = new ProductoBD(this, "Parcial", null, 1);
        List<Producto> productos = pbd.consultarProductos();

        String lista[] = new String[productos.size()];

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
        int id=spinner.getSelectedItemPosition();
        ProductoBD pbd=new ProductoBD(this,"Parcial",null,1);
        Producto producto=pbd.consultarId(id);


        String nombre=producto.getItem();
        int cantidad;
        if (spinner.getSelectedItemPosition() == 1) {
            cantidad = Integer.parseInt(txtentrega.getText().toString().trim());
        }else{
            cantidad = Integer.parseInt(txtcantidad.getText().toString().trim());
        }
        int preciop=cantidad*producto.getPrecio();
        ids.add(id);
        nombres.add(nombre);
        cantidades.add(cantidad);
        precio.add(preciop);

        Intent intent=new Intent(CrearVentaActivity.this,MainActivity.class);
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_CLEAR_TASK);
        Bundle bundle=new Bundle();
        bundle.putIntegerArrayList("ids",ids);
        bundle.putIntegerArrayList("cantidades",cantidades);
        bundle.putStringArrayList("nombres",nombres);
        bundle.putIntegerArrayList("precio",precio);
        intent.putExtras(bundle);
        startActivity(intent);
    }



}
