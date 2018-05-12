/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univalle.parcial.parcial.modelo;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Venta  {
    private int id;
    private Cliente cliente;
    private Producto producto;
    private String fecha;
    private int cantidad;
    private int total;

    public Venta(int id, Cliente cliente, Producto producto, String fecha,int cantidad, int total ) {
        this.id=id;
        this.cliente = cliente;
        this.setProducto(producto);
        this.setCantidad(cantidad);
        this.fecha=fecha;
        this.total = total;
        
    }


    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


}
