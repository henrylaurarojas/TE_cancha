package com.emergentes.controlador;
import com.emergentes.modelo.Reserva;
import com.emergentes.utiles.ConexionDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PreparedStatement ps;
        ConexionDB canal = new ConexionDB();
        Connection conn = canal.conectar();
        ResultSet rs;
        String op;
        ArrayList<Reserva> lista = new ArrayList<Reserva>();
        int idr;
        
        op = (request.getParameter("op") != null) ? request.getParameter("op") : "list";
        
        if(op.equals("list")){
            //Operaciones para listar datos
            String sql = "select * from reserva";
            try {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                    Reserva re = new Reserva();
                    re.setIdr(rs.getInt("idr"));
                    re.setFecha(rs.getString("fecha"));
                    re.setDesde(rs.getString("desde"));
                    re.setHasta(rs.getString("hasta"));
                    re.setIdcl(rs.getInt("idcl"));
                    re.setIde(rs.getInt("ide"));
                    re.setIdcan(rs.getInt("idcan"));
                    re.setObs(rs.getString("obs"));
                    
                    //adicionamos a la lista
                    lista.add(re);
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(op.equals("nuevo")){
            //operaciones para desplegar formulario
            Reserva re = new Reserva();
            //Utilizamos otros atributo en este caso el formulario
            request.setAttribute("res", re);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }
        if(op.equals("editar")){
            idr = Integer.parseInt(request.getParameter("idr")); //estamos recbiendo el valor del id para editar
            try {
                Reserva res1 = new Reserva();
                ps = conn.prepareStatement("select * from reserva where idr = ?");
                ps.setInt(1, idr);
                rs = ps.executeQuery();
                
                if(rs.next()){
                    res1.setIdr(rs.getInt("idr"));
                    res1.setFecha(rs.getString("fecha"));
                    res1.setDesde(rs.getString("desde"));
                    res1.setHasta(rs.getString("hasta"));
                    res1.setIdcl(rs.getInt("idcl"));
                    res1.setIde(rs.getInt("ide"));
                    res1.setIdcan(rs.getInt("idcan"));
                    res1.setObs(rs.getString("obs"));
                }
                request.setAttribute("res", res1);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(op.equals("eliminar")){
            //operaciones para eliminar un registro
            idr = Integer.parseInt(request.getParameter("idr")); //estamos recbiendo el valor del id para eliminar
            try {                
                ps = conn.prepareStatement("delete from reserva where idr = ? ");
                ps.setInt(1, idr);
                
                ps.executeUpdate();
                response.sendRedirect("MainController");
                        } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idr = Integer.parseInt(request.getParameter(("idr"))); //recuperamos el valor del id
        String fecha = request.getParameter("fecha");
        String desde = request.getParameter("desde");
        String hasta = request.getParameter("hasta");
        int idcl = Integer.parseInt(request.getParameter(("idcl")));
        int ide = Integer.parseInt(request.getParameter(("ide")));
        int idcan = Integer.parseInt(request.getParameter(("idcan")));
        String obs = request.getParameter("obs");
        
        Reserva res = new Reserva();
        res.setIdr(idr);
        res.setFecha(fecha);
        res.setDesde(desde);
        res.setHasta(hasta);
        res.setIdcl(idcl);
        res.setIde(ide);
        res.setIdcan(idcan);
        res.setObs(obs);
        
        ConexionDB canal = new ConexionDB();
        Connection conn = canal.conectar();
        PreparedStatement ps;
        ResultSet rs;
        
        if(idr == 0){
            //insertar registro
                String sql = "insert into reserva (idr, fecha, desde, hasta, idcl, ide, idcan, obs) values (?, ?, ?, ?, ?, ?, ?, ?)";
            try {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, res.getIdr());
                ps.setString(2, res.getFecha());
                ps.setString(3, res.getDesde());
                ps.setString(4, res.getHasta());
                ps.setInt(5, res.getIdcl());
                ps.setInt(6, res.getIde());
                ps.setInt(7, res.getIdcan());
                ps.setString(8, res.getObs());
                
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            //actualizar registro
                String sql1 = "update reserva set fecha=?, desde=?, hasta=?, idcl=?, ide=?, idcan=?, obs=? where idr=?";
            try {   
                ps = conn.prepareStatement(sql1);
                ps.setString(1, res.getFecha());
                ps.setString(2, res.getDesde());
                ps.setString(3, res.getHasta());
                ps.setInt(4, res.getIdcl());
                ps.setInt(5, res.getIde());
                ps.setInt(6, res.getIdcan());
                ps.setString(7, res.getObs());
                ps.setInt(8, res.getIdr());
                
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //lamada al index.jsp
        response.sendRedirect("MainController");
    }
}