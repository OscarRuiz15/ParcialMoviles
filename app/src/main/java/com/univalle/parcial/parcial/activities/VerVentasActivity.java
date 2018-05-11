package com.univalle.parcial.parcial.activities;

import android.app.AlertDialog;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.univalle.parcial.parcial.R;
import com.univalle.parcial.parcial.conexion.VentaBD;
import com.univalle.parcial.parcial.fragments.DetallesVenta;
import com.univalle.parcial.parcial.modelo.Producto;
import com.univalle.parcial.parcial.modelo.Venta;

import java.util.ArrayList;
import java.util.List;

public class VerVentasActivity extends AppCompatActivity implements DetallesVenta.OnFragmentInteractionListener{

    private ArrayAdapter<String> adaptador;
    private ListView ventasList;
    List<Venta> ventas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_ventas);


        VentaBD vd=new VentaBD(this, "Parcial", null, 1);
        ventas=vd.consultarVentas(this);

        ArrayList<String>datosVenta=new ArrayList<String>();
        int totalVentas=0;

        for (int i = 0; i < ventas.size(); i++) {
            datosVenta.add("Venta #"+ventas.get(i).getId()+"\nCliente: "+ventas.get(i).getCliente().getNombre()+" "+ventas.get(i).getCliente().getApellido()+"\nTotal: "+ventas.get(i).getTotal()+"\nFecha: "+ventas.get(i).getFecha());
            totalVentas+=ventas.get(i).getTotal();
        }
        datosVenta.add("Total Ventas: "+totalVentas);

        adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datosVenta);
        ventasList=(ListView)findViewById(R.id.ventasList);
        ventasList.setAdapter(adaptador);

        ventasList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String componente= ventasList.getAdapter().getItem(position).toString();
                //Toast.makeText(getApplication(),"Hola: "+componente+" "+position, Toast.LENGTH_SHORT).show();

                String nombreCliente=ventas.get(position).getCliente().getNombre()+" "+ventas.get(position).getCliente().getApellido();
                Producto pro=ventas.get(position).getProducto();
                String productos=""+pro.getItem();

                Toast.makeText(getApplication(),""+nombreCliente+"\n"+productos, Toast.LENGTH_SHORT).show();
                /*DetallesVenta fragment = DetallesVenta.newInstance(ventas);
                android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameVentas,fragment);
                ft.addToBackStack(null);
                ft.commit();*/
            }
        });

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
