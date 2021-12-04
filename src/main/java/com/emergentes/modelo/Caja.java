package com.emergentes.modelo;
public class Caja {
    private int id;
    private String fecha;
    private int idr;
    private int monto;
    public Caja() {
        this.id=0;
        this.fecha="";
        this.idr=0;
        this.monto=0;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public int getIdr() {
        return idr;
    }
    public void setIdr(int idr) {
        this.idr = idr;
    }
    public int getMonto() {
        return monto;
    }
    public void setMonto(int monto) {
        this.monto = monto;
    }
    @Override
    public String toString() {
        return "Caja{" + "id=" + id + ", fecha=" + fecha + ", idr=" + idr + ", monto=" + monto + '}';
    }    
}
