package com.univalle.parcial.parcial.activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.univalle.parcial.parcial.R;
import com.univalle.parcial.parcial.conexion.ConexionBD;
import com.univalle.parcial.parcial.fragments.ConsultarVentasCliente;
import com.univalle.parcial.parcial.fragments.Registro_Producto;
import com.univalle.parcial.parcial.fragments.VerVentas;
import com.univalle.parcial.parcial.modelo.Cliente;

public class MainActivity extends AppCompatActivity implements ConsultarVentasCliente.OnFragmentInteractionListener,Registro_Producto.OnFragmentInteractionListener,VerVentas.OnFragmentInteractionListener {

    private TextView mTextMessage;
    Cliente cliente;
    ConexionBD conexion;
    SQLiteDatabase db;
    ConsultarVentasCliente consultventcliente;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_consultar_venta_cliente:
                    crearFragmentConsultarCliente();
                    return true;
                case R.id.navigation_consultar_ventas:
                    crearFragmentVentasTotales();
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
        ConsultarVentasCliente fragment = ConsultarVentasCliente.newInstance();
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment,fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void crearFragmentVentasTotales(){
        VerVentas fragment = VerVentas.newInstance();
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment,fragment);
        ft.addToBackStack(null);
        ft.commit();
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

    ////////MÉTODOS NECESARIOS PARA INFLAR LA VISTA CON UN MENU//////

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate activity menu items.
        getMenuInflater().inflate(R.menu.logout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemSalir:
                Intent intent = new Intent(MainActivity.this, ActivityLogin.class);
                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                //Toast.makeText(this,"hola "+item.getTitle(),Toast.LENGTH_LONG).show();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }
}