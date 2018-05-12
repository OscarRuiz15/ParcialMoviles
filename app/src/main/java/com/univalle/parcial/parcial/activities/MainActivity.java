package com.univalle.parcial.parcial.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.univalle.parcial.parcial.R;
import com.univalle.parcial.parcial.fragments.ConsultarVentasCliente;
import com.univalle.parcial.parcial.fragments.RegistrarCliente;
import com.univalle.parcial.parcial.fragments.Registro_Producto;
import com.univalle.parcial.parcial.fragments.VerVentas;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ConsultarVentasCliente.OnFragmentInteractionListener,VerVentas.OnFragmentInteractionListener, Registro_Producto.OnFragmentInteractionListener, RegistrarCliente.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ///Publicidad
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener(){});
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            Intent intent = new Intent(MainActivity.this, ActivityLogin.class);
            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nagitacion_ventasCliente) {
            crearFragmentConsultarCliente();
        } else if (id == R.id.navigation_consultarVentas) {
            crearFragmentVentasTotales();
        }
        else if (id == R.id.navigation_registrarCliente) {
            crearFragmentRegistrarCliente();
        }
        else if (id == R.id.navigation_registrarProducto) {
            crearFragmentRegistrarProducto();
        }
        else if (id == R.id.navigation_registrarVenta) {
            //RegistrarVenta
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

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

    public void crearFragmentRegistrarProducto(){
        Registro_Producto fragment = Registro_Producto.newInstance("","");
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment,fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void crearFragmentRegistrarCliente(){
        RegistrarCliente fragment = RegistrarCliente.newInstance("","");
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment,fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
