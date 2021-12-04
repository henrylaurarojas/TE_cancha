package com.emergentes.controlador;
import com.emergentes.modelo.Caja;
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
@WebServlet(name = "MainControllerCaja", urlPatterns = {"/MainControllerCaja"})
public class MainControllerCaja extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PreparedStatement ps;
        ConexionDB canal = new ConexionDB();
        Connection conn = canal.conectar();
        ResultSet rs;
        String op;
        ArrayList<Caja> lista = new ArrayList<Caja>();
        int id;
        
        op = (request.getParameter("op") != null) ? request.getParameter("op") : "list";
        
        if(op.equals("list")){
            //Operaciones para listar datos
            String sql = "select * from caja";
            try {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                    Caja ca = new Caja();
                    ca.setId(rs.getInt("id"));
                    ca.setFecha(rs.getString("fecha"));
                    ca.setIdr(rs.getInt("idr"));
                    ca.setMonto(rs.getInt("monto"));
                    
                    //adicionamos a la lista
                    lista.add(ca);
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("caja.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //if(op.equals("nuevo")){
            //int idr = Integer.parseInt(request.getParameter("idr"));
            //id = Integer.parseInt(request.getParameter("idr")); //estamos recbiendo el valor del id para editar
            //operaciones para desplegar formulario
            //Caja ca = new Caja();
            //Utilizamos otros atributo en este caso el formulario
            //request.setAttribute("caj", ca);
            //request.getRequestDispatcher("editarcaja.jsp").forward(request, response);
        //}
        if(op.equals("nuevo")){
            int idr = Integer.parseInt(request.getParameter("idr"));
            id = Integer.parseInt(request.getParameter("idr")); //estamos recbiendo el valor del id para editar
            try {
                Caja caj1 = new Caja();
                ps = conn.prepareStatement("select * from caja where id = ?");
                ps.setInt(1, id);
                rs = ps.executeQuery();
                
                if(rs.next()){
                    caj1.setId(rs.getInt("id"));
                    //caj1.setFecha(rs.getString("fecha"));
                    caj1.setIdr(rs.getInt("idr"));
                    //caj1.setMonto(rs.getInt("monto"));
                }
                request.setAttribute("caj", caj1);
                request.getRequestDispatcher("editarcaja.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter(("id"))); //recuperamos el valor del id
        String fecha = request.getParameter("fecha");
        int idr = Integer.parseInt(request.getParameter(("idr")));
        int monto = Integer.parseInt(request.getParameter(("monto")));
        
        Caja caj = new Caja();
        caj.setId(id);
        caj.setFecha(fecha);
        caj.setIdr(idr);
        caj.setMonto(monto);
        
        ConexionDB canal = new ConexionDB();
        Connection conn = canal.conectar();
        PreparedStatement ps;
        ResultSet rs;
        
        if(id == 0){
            //insertar registro
                String sql = "insert into caja (id, fecha, idr, monto) values (?, ?, ?, ?)";
            try {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, caj.getId());
                ps.setString(2, caj.getFecha());
                ps.setInt(3, caj.getIdr());
                ps.setInt(4, caj.getMonto());               
                
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //lamada al index.jsp
        response.sendRedirect("MainControllerCaja");
    }
}
