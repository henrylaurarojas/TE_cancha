package com.emergentes.controlador;
import com.emergentes.modelo.Cancha;
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
@WebServlet(name = "MainControllerCanchas", urlPatterns = {"/MainControllerCanchas"})
public class MainControllerCanchas extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PreparedStatement ps;
        ConexionDB canal = new ConexionDB();
        Connection conn = canal.conectar();
        ResultSet rs;
        String op;
        ArrayList<Cancha> lista = new ArrayList<Cancha>();
        int idcan;
        
        op = (request.getParameter("op") != null) ? request.getParameter("op") : "list";
        
        if(op.equals("list")){
            //Operaciones para listar datos
            String sql = "select * from cancha";
            try {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                    Cancha ca = new Cancha();
                    ca.setIdcan(rs.getInt("idcan"));
                    ca.setNombre(rs.getString("nombre"));
                    ca.setPrecio(rs.getInt("precio"));
                    ca.setObs(rs.getString("obs"));
                    
                    //adicionamos a la lista
                    lista.add(ca);
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("cancha.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(op.equals("nuevo")){
            //operaciones para desplegar formulario
            Cancha ca = new Cancha();
            //Utilizamos otros atributo en este caso el formulario
            request.setAttribute("can", ca);
            request.getRequestDispatcher("editarcancha.jsp").forward(request, response);
        }
        if(op.equals("editar")){
            idcan = Integer.parseInt(request.getParameter("idcan")); //estamos recbiendo el valor del id para editar
            try {
                Cancha can1 = new Cancha();
                ps = conn.prepareStatement("select * from cancha where idcan = ?");
                ps.setInt(1, idcan);
                rs = ps.executeQuery();
                
                if(rs.next()){
                    can1.setIdcan(rs.getInt("idcan"));
                    can1.setNombre(rs.getString("nombre"));
                    can1.setPrecio(rs.getInt("precio"));
                    can1.setObs(rs.getString("obs"));
                }
                request.setAttribute("can", can1);
                request.getRequestDispatcher("editarcancha.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(op.equals("eliminar")){
            //operaciones para eliminar un registro
            idcan = Integer.parseInt(request.getParameter("idcan")); //estamos recbiendo el valor del id para eliminar
            try {                
                ps = conn.prepareStatement("delete from cancha where idcan = ? ");
                ps.setInt(1, idcan);
                
                ps.executeUpdate();
                response.sendRedirect("MainControllerCanchas");
                        } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idcan = Integer.parseInt(request.getParameter(("idcan"))); //recuperamos el valor del id
        String nombre = request.getParameter("nombre");
        int precio = Integer.parseInt(request.getParameter(("precio")));
        String obs = request.getParameter("obs");
        
        Cancha can = new Cancha();
        can.setIdcan(idcan);
        can.setNombre(nombre);
        can.setPrecio(precio);
        can.setObs(obs);
        
        ConexionDB canal = new ConexionDB();
        Connection conn = canal.conectar();
        PreparedStatement ps;
        ResultSet rs;
        
        if(idcan == 0){
            //insertar registro
                String sql = "insert into cancha (idcan, nombre, precio, obs) values (?, ?, ?, ?)";
            try {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, can.getIdcan());
                ps.setString(2, can.getNombre());
                ps.setInt(3, can.getPrecio());
                ps.setString(4, can.getObs());
                
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            //actualizar registro
                String sql1 = "update cancha set nombre=?, precio=?, obs=? where idcan=?";
            try {   
                ps = conn.prepareStatement(sql1);
                ps.setString(1, can.getNombre());
                ps.setInt(2, can.getPrecio());
                ps.setString(3, can.getObs());
                ps.setInt(4, can.getIdcan());
                
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //lamada al index.jsp
        response.sendRedirect("MainControllerCanchas");
    }
}
