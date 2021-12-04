package com.emergentes.modelo;
public class Cliente {
    private int idcl;
    private String nombre;
    private String apellido;
    private String ci;
    private String celular;
    public Cliente() {
        this.idcl=0;
        this.nombre="";
        this.apellido="";
        this.ci="";
        this.celular="";
    }
    public int getIdcl() {
        return idcl;
    }
    public void setIdcl(int idcl) {
        this.idcl = idcl;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getCi() {
        return ci;
    }
    public void setCi(String ci) {
        this.ci = ci;
    }
    public String getCelular() {
        return celular;
    }
    public void setCelular(String celular) {
        this.celular = celular;
    }
    @Override
    public String toString() {
        return "Cliente{" + "idcl=" + idcl + ", nombre=" + nombre + ", apellido=" + apellido + ", ci=" + ci + ", celular=" + celular + '}';
    }   
}
