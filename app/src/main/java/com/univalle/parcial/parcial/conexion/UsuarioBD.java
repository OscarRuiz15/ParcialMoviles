package com.univalle.parcial.parcial.conexion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.univalle.parcial.parcial.modelo.Cliente;
import com.univalle.parcial.parcial.modelo.Usuario;

public class UsuarioBD extends ConexionBD{
    private SQLiteDatabase db;

    public  UsuarioBD (Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        db=super.getWritableDatabase();
    }

    public Usuario verificarID(String username, String password) {
        Usuario u = null;
        String pass= md5(password);
        String query = "select * from usuario where username='" + username + "' and password='" + pass + "';";
        Cursor fila = db.rawQuery(query, null);
        if (fila.moveToFirst()) {
            int id = fila.getInt(0);
            String nombre = fila.getString(1);
            String contrasena = fila.getString(1);
            u = new Usuario(id, nombre, contrasena);
        }
        return u;
    }

    public boolean insertarUsuario(Usuario u){
        ContentValues registro = new ContentValues();
        registro.put("id",u.getId());
        registro.put("username", u.getUsername());
        registro.put("password", u.getPassword());

        db.insert("usuario", null, registro);
        db.close();

        return true;
    }



    public String md5 (String md5pass) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5pass.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
