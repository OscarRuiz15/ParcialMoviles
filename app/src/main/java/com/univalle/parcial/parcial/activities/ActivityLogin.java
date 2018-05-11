package com.univalle.parcial.parcial.activities;

import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.univalle.parcial.parcial.R;
import com.univalle.parcial.parcial.conexion.ConexionBD;
import com.univalle.parcial.parcial.conexion.UsuarioBD;
import com.univalle.parcial.parcial.modelo.Usuario;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener{

    private EditText txtusername, txtpassword;
    ConexionBD conexion;
    SQLiteDatabase db;

    boolean isMobile,IsWifi=false;
    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    private GoogleSignInClient mGoogleSignInClient;
    private GoogleApiClient googleApiClient;

    AccountManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ///Componentes interfaz
        txtusername = (EditText) findViewById(R.id.txtusername);
        txtpassword = (EditText) findViewById(R.id.txtpassword);
        txtusername.setText("admin");
        txtpassword.setText("admin");

        ///Conexion a BD para crearla o verificar que ya exista
        conexion = new ConexionBD(this, "Parcial", null, 1);
        String DB_PATH = "/data/data/com.univalle.parcial.parcial/databases/Parcial";
        try {
            db = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READONLY);
            db.close();
            //Toast.makeText(getApplication(), "Ya existe la BD", Toast.LENGTH_LONG).show();
        } catch (SQLiteException e) {
            //Si no existe la BD
            db = conexion.getWritableDatabase();
            if (conexion != null) {
                //Toast.makeText(getApplication(), "BD creada", Toast.LENGTH_LONG).show();
                String query;
                UsuarioBD u = new UsuarioBD(this, "Parcial", null, 1);
                String pass = u.md5("admin");
                query = "insert into usuario (id , username, password) values (0,'admin','" + pass + "');";
                db.execSQL(query);
            }
        }

        ///Para el login a traves del API de Google
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

        manager = AccountManager.get(this);

        findViewById(R.id.sign_in).setOnClickListener(this);

        ///Publicidad
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener(){});

    }

    public void ingresarSistema(View view) {
        String username = txtusername.getText().toString().trim();
        String pass = txtpassword.getText().toString().trim();


        if (username.equals("") || pass.equals("")) {
            /*String message = "Hay campos vacios";
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setMessage(message);
            alertDialog.show();*/
            final AlertDialog dialogo = new AlertDialog.Builder(this).create();
            dialogo.setTitle("Acceso no permitido");
            dialogo.setMessage("Por favor registrese");
            dialogo.setButton(DialogInterface.BUTTON_POSITIVE, "Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            dialogo.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogo.dismiss();
                }
            });
            dialogo.show();
        } else {
            UsuarioBD u = new UsuarioBD(this, "Parcial", null, 1);
            Usuario us = u.verificarID(username, pass);
            if (us != null) {
                    Intent intent = new Intent(ActivityLogin.this, MainActivity.class);
                    intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_CLEAR_TASK);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", us.getId());
                    bundle.putString("username", us.getUsername());
                    intent.putExtras(bundle);
                    startActivity(intent);
                //Toast.makeText(getApplication(), "Se logea papu", Toast.LENGTH_LONG).show();
            } else {
                final AlertDialog dialogo = new AlertDialog.Builder(this).create();
                dialogo.setTitle("Usuario o contraseña incorrecto");
                dialogo.setButton(DialogInterface.BUTTON_POSITIVE, "Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialogo.show();
                txtusername.setText("");
                txtpassword.setText("");
            }
        }
    }
    //////////////////////////TODO LO QUE TIENE QUE VER GON GOOGLE
    public  boolean checkConnection(){
        Toast.makeText(this,"Verificando conexion...",Toast.LENGTH_SHORT).show();
        boolean isConnet=false;
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if(activeNetwork!=null){
            isConnet = activeNetwork.isConnectedOrConnecting();
        }
        if(isConnet && activeNetwork!=null){
            isMobile= activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE;
            IsWifi = activeNetwork.getType()== ConnectivityManager.TYPE_WIFI;
            return  true;
        }else{
            return  false;
        }
    }
    private  void signIn(){
        if(checkConnection()){
            Toast.makeText(this,"Conexion exitosa",Toast.LENGTH_SHORT).show();
            if(isMobile){
                Toast.makeText(this,"Conexion por Datos",Toast.LENGTH_SHORT).show();
                android.support.v7.app.AlertDialog dialogo = new android.support.v7.app.AlertDialog.Builder(this).create();
                dialogo.setTitle("Validar red");
                dialogo.setMessage("Desea consumir datos");
                dialogo.setCancelable(false);
                dialogo.setButton(DialogInterface.BUTTON_POSITIVE,"Aceptar",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent signIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                        startActivityForResult(signIntent,RC_SIGN_IN);
                    }
                });
                dialogo.setButton(DialogInterface.BUTTON_NEGATIVE, "La proxima", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialogo.show();
            }else{
                Toast.makeText(this,"Conexion Tipo Wifi",Toast.LENGTH_SHORT).show();
                Intent signIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(signIntent,RC_SIGN_IN);
            }
        }else{
            android.support.v7.app.AlertDialog dialogo = new android.support.v7.app.AlertDialog.Builder(this).create();
            dialogo.setTitle("Sin conexion");
            dialogo.setMessage("No puede ingresar sin estar conectado");
            dialogo.setButton(DialogInterface.BUTTON_NEUTRAL, "Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialogo.show();
        }
    }
    private void handleSignInResult(GoogleSignInResult result ) {
        if(result.isSuccess()){
            Intent intent = new Intent(ActivityLogin.this, MainActivity.class);
            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (RC_SIGN_IN==requestCode){
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            GoogleSignInResult resul = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            //Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(resul);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign_in:
                signIn();
                break;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
