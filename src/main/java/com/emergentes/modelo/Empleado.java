package com.emergentes.modelo;
public class Empleado {
    private int ide;
    private String nombre;
    private String ci;
    private String pass;
    private String celular;
    public Empleado() {
        this.ide=0;
        this.nombre="";
        this.ci="";
        this.pass="";
        this.celular="";
    }
    public int getIde() {
        return ide;
    }
    public void setIde(int ide) {
        this.ide = ide;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCi() {
        return ci;
    }
    public void setCi(String ci) {
        this.ci = ci;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public String getCelular() {
        return celular;
    }
    public void setCelular(String celular) {
        this.celular = celular;
    }
    @Override
    public String toString() {
        return "Empleado{" + "ide=" + ide + ", nombre=" + nombre + ", ci=" + ci + ", pass=" + pass + ", celular=" + celular + '}';
    }
}
