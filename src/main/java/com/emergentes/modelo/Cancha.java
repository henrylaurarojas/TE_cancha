package com.emergentes.modelo;
public class Cancha {
    private int idcan;
    private String nombre;
    private int precio;
    private String obs;
    public Cancha() {
        this.idcan=0;
        this.nombre="";
        this.precio=0;
        this.obs="";
    }
    public int getIdcan() {
        return idcan;
    }
    public void setIdcan(int idcan) {
        this.idcan = idcan;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getPrecio() {
        return precio;
    }
    public void setPrecio(int precio) {
        this.precio = precio;
    }
    public String getObs() {
        return obs;
    }
    public void setObs(String obs) {
        this.obs = obs;
    }
    @Override
    public String toString() {
        return "Cancha{" + "idcan=" + idcan + ", nombre=" + nombre + ", precio=" + precio + ", obs=" + obs + '}';
    }
    
}
