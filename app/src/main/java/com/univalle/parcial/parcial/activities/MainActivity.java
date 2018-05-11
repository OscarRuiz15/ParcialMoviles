package com.univalle.parcial.parcial.activities;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
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

public class MainActivity extends AppCompatActivity implements ConsultarVentasClinte.OnFragmentInteractionListener {

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
                    Toast.makeText(getApplication(), "clic registrar", Toast.LENGTH_LONG).show();

                    crearFragmentConsultarCliente();
                    return true;
                case R.id.navigation_consultar_ventas:
                    Toast.makeText(getApplication(), "clic consultar", Toast.LENGTH_LONG).show();

                    crearFragmentConsultarCliente();
                    return true;
            }
            return false;
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.navigation_dashboard:
                crearFragmentConsultarCliente();
                return true;
            case R.id.navigation_home:
                crearFragmentConsultarCliente();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("id");
        String nombre = bundle.getString("nombre");
        String email = bundle.getString("email");
        String apellido= bundle.getString("apellido");
        cliente = new Cliente(id, nombre, apellido, email);

        /*if (tipo == 0) {
            crearFragmentRegistrar();
        }*/

        setContentView(R.layout.activity_main);

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
        Toast.makeText(getApplication(), "Consultar", Toast.LENGTH_LONG).show();

        /*Bundle bundle = new Bundle();
        bundle.putInt("id", usuario.getId());
        bundle.putString("nombre", usuario.getNombre());
        bundle.putString("email", usuario.getEmail());
        bundle.putInt("tipo", usuario.getTipo());*/

        Cursor c = db.rawQuery("SELECT * FROM usuarios", null);
        ArrayList<String> clientes= new ArrayList<String>();
        if(c.moveToFirst()){
            do {
                clientes.add(c.getString(1));
            }while (c.moveToNext());
        }
        c.close();

        consultventcliente = new ConsultarVentasClinte();
        /*android.support.v4.app.FragmentTransaction transaccion = this.getSupportFragmentManager().beginTransaction();
        transaccion.replace(R.id.fragmentA,fragcon);
        transaccion.addToBackStack(null);*/
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment, consultventcliente);
        ft.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}