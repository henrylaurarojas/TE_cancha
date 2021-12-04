package com.emergentes.controlador;
import com.emergentes.modelo.Empleado;
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
@WebServlet(name = "MainControllerEmpleados", urlPatterns = {"/MainControllerEmpleados"})
public class MainControllerEmpleados extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PreparedStatement ps;
        ConexionDB canal = new ConexionDB();
        Connection conn = canal.conectar();
        ResultSet rs;
        String op;
        ArrayList<Empleado> lista = new ArrayList<Empleado>();
        int ide;
        
        op = (request.getParameter("op") != null) ? request.getParameter("op") : "list";
        
        if(op.equals("list")){
            //Operaciones para listar datos
            String sql = "select * from empleado";
            try {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                    Empleado em = new Empleado();
                    em.setIde(rs.getInt("ide"));
                    em.setNombre(rs.getString("nombre"));
                    em.setCi(rs.getString("ci"));
                    em.setPass(rs.getString("pass"));
                    em.setCelular(rs.getString("celular"));
                    
                    //adicionamos a la lista
                    lista.add(em);
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("empleado.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(op.equals("nuevo")){
            //operaciones para desplegar formulario
            Empleado em = new Empleado();
            //Utilizamos otros atributo en este caso el formulario
            request.setAttribute("emp", em);
            request.getRequestDispatcher("editarempleado.jsp").forward(request, response);
        }
        if(op.equals("editar")){
            ide = Integer.parseInt(request.getParameter("ide")); //estamos recbiendo el valor del id para editar
            try {
                Empleado emp1 = new Empleado();
                ps = conn.prepareStatement("select * from empleado where ide = ?");
                ps.setInt(1, ide);
                rs = ps.executeQuery();
                
                if(rs.next()){
                    emp1.setIde(rs.getInt("ide"));
                    emp1.setNombre(rs.getString("nombre"));
                    emp1.setCi(rs.getString("ci"));
                    emp1.setPass(rs.getString("pass"));
                    emp1.setCelular(rs.getString("celular"));
                }
                request.setAttribute("emp", emp1);
                request.getRequestDispatcher("editarempleado.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(op.equals("eliminar")){
            //operaciones para eliminar un registro
            ide = Integer.parseInt(request.getParameter("ide")); //estamos recbiendo el valor del id para eliminar
            try {                
                ps = conn.prepareStatement("delete from empleado where ide = ? ");
                ps.setInt(1, ide);
                
                ps.executeUpdate();
                response.sendRedirect("MainControllerEmpleados");
                        } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int ide = Integer.parseInt(request.getParameter(("ide"))); //recuperamos el valor del id
        String nombre = request.getParameter("nombre");
        String ci = request.getParameter("ci");
        String pass = request.getParameter("pass");
        String celular = request.getParameter("celular");
        
        Empleado emp = new Empleado();
        emp.setIde(ide);
        emp.setNombre(nombre);
        emp.setCi(ci);
        emp.setPass(pass);
        emp.setCelular(celular);
        
        ConexionDB canal = new ConexionDB();
        Connection conn = canal.conectar();
        PreparedStatement ps;
        ResultSet rs;
        
        if(ide == 0){
            //insertar registro
                String sql = "insert into empleado (ide, nombre, ci, pass, celular) values (?, ?, ?, ?, ?)";
            try {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, emp.getIde());
                ps.setString(2, emp.getNombre());
                ps.setString(3, emp.getCi());
                ps.setString(4, emp.getPass());
                ps.setString(5, emp.getCelular());
                
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            //actualizar registro
                String sql1 = "update empleado set nombre=?, ci=?, pass=?, celular=? where ide=?";
            try {   
                ps = conn.prepareStatement(sql1);
                ps.setString(1, emp.getNombre());
                ps.setString(2, emp.getCi());
                ps.setString(3, emp.getPass());
                ps.setString(4, emp.getCelular());
                ps.setInt(5, emp.getIde());
                
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //lamada al index.jsp
        response.sendRedirect("MainControllerEmpleados");
    }
}
