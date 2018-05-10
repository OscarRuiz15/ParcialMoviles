 
package com.univalle.parcial.parcial.modelo;

public class Producto {
    private int id;
    private String item;
    private int precio;

    public Producto(int id, String item, int precio) {
        this.id = id;
        this.item = item;
        this.precio = precio;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    
    
    
}
