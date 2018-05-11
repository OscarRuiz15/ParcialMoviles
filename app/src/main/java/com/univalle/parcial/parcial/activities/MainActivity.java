package com.univalle.parcial.parcial.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.univalle.parcial.parcial.R;
import com.univalle.parcial.parcial.conexion.ConexionBD;
import com.univalle.parcial.parcial.fragments.ConsultarVentasClinte;
import com.univalle.parcial.parcial.fragments.Registro_Producto;
import com.univalle.parcial.parcial.modelo.Cliente;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ConsultarVentasClinte.OnFragmentInteractionListener,Registro_Producto.OnFragmentInteractionListener {

    private TextView mTextMessage;
    Cliente cliente;
    ConexionBD conexion;
    SQLiteDatabase db;
    ConsultarVentasClinte consultventcliente;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_consultar_venta_cliente:
                    Toast.makeText(getApplication(), "Felipe", Toast.LENGTH_LONG).show();
                    crearFragmentConsultarCliente();
                    return true;
                case R.id.navigation_consultar_ventas:
                    Toast.makeText(getApplication(), "clic consultar", Toast.LENGTH_LONG).show();
                    consultarVentas();
                    return true;
                case R.id.navigation_registrar_producto:
                    registrarProducto();
                    return true;
                case R.id.navigation_home:
                    //crearFragmentConsultarCliente();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        /*Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("id");
        String nombre = bundle.getString("nombre");
        String email = bundle.getString("email");
        String apellido= bundle.getString("apellido");
        cliente = new Cliente(id, nombre, apellido, email);*/

        /*if (tipo == 0) {
            crearFragmentRegistrar();
        }*/

        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    /* public void crearFragmentRegistrar() {
         Toast.makeText(getApplication(), "Registrar", Toast.LENGTH_LONG).show();

         Bundle bundle = new Bundle();
         bundle.putInt("id", cliente.getId());
         bundle.putString("nombre", cliente.getNombre());
         bundle.putString("apellido", cliente.getApellido()));
         bundle.putString("email", cliente.getEmail());

         FragmentRegistrar fragment = FragmentRegistrar.newInstance(bundle);
         android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
         ft.add(R.id.fragment, fragment);


         ft.commit();
     }
 */
    public void crearFragmentConsultarCliente() {
        /*List<Cliente> clientes;
        ClienteBD cbd=new ClienteBD(this, "Parcial", null, 1);
        clientes=cbd.consultarClientes();*/

        ConsultarVentasClinte fragment = ConsultarVentasClinte.newInstance();

        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment,fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void consultarVentas(){
        Toast.makeText(getApplication(), "Vamos", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainActivity.this, VerVentasActivity.class);
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void registrarProducto(){
        Registro_Producto fragment = Registro_Producto.newInstance("","");

        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment,fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}