package com.emergentes.modelo;
public class Reserva {
    private int idr;
    private String fecha;
    private String desde;
    private String hasta;
    private int idcl;
    private int ide;
    private int idcan;
    private String obs;
    public Reserva() {
        this.idr=0;
        this.fecha="";
        this.desde="";
        this.hasta="";
        this.idcl=0;
        this.ide=0;
        this.idcan=0;
        this.obs="";        
    }
    public int getIdr() {
        return idr;
    }
    public void setIdr(int idr) {
        this.idr = idr;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getDesde() {
        return desde;
    }
    public void setDesde(String desde) {
        this.desde = desde;
    }
    public String getHasta() {
        return hasta;
    }
    public void setHasta(String hasta) {
        this.hasta = hasta;
    }
    public int getIdcl() {
        return idcl;
    }
    public void setIdcl(int idcl) {
        this.idcl = idcl;
    }
    public int getIde() {
        return ide;
    }
    public void setIde(int ide) {
        this.ide = ide;
    }
    public int getIdcan() {
        return idcan;
    }
    public void setIdcan(int idcan) {
        this.idcan = idcan;
    }
    public String getObs() {
        return obs;
    }
    public void setObs(String obs) {
        this.obs = obs;
    }
    @Override
    public String toString() {
        return "Reserva{" + "idr=" + idr + ", fecha=" + fecha + ", desde=" + desde + ", hasta=" + hasta + ", idcl=" + idcl + ", ide=" + ide + ", idcan=" + idcan + ", obs=" + obs + '}';
    }      
}
