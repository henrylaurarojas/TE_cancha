package com.emergentes.controlador;
import com.emergentes.modelo.Cliente;
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
@WebServlet(name = "MainControllerClientes", urlPatterns = {"/MainControllerClientes"})
public class MainControllerClientes extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PreparedStatement ps;
        ConexionDB canal = new ConexionDB();
        Connection conn = canal.conectar();
        ResultSet rs;
        String op;
        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        int idcl;
        
        op = (request.getParameter("op") != null) ? request.getParameter("op") : "list";
        
        if(op.equals("list")){
            //Operaciones para listar datos
            String sql = "select * from cliente";
            try {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                    Cliente cl = new Cliente();
                    cl.setIdcl(rs.getInt("idcl"));
                    cl.setNombre(rs.getString("nombre"));
                    cl.setApellido(rs.getString("apellido"));
                    cl.setCi(rs.getString("ci"));
                    cl.setCelular(rs.getString("celular"));
                    
                    //adicionamos a la lista
                    lista.add(cl);
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("cliente.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(op.equals("nuevo")){
            //operaciones para desplegar formulario
            Cliente cl = new Cliente();
            //Utilizamos otros atributo en este caso el formulario
            request.setAttribute("cli", cl);
            request.getRequestDispatcher("editarcliente.jsp").forward(request, response);
        }
        if(op.equals("editar")){
            idcl = Integer.parseInt(request.getParameter("idcl")); //estamos recbiendo el valor del id para editar
            try {
                Cliente cli1 = new Cliente();
                ps = conn.prepareStatement("select * from cliente where idcl = ?");
                ps.setInt(1, idcl);
                rs = ps.executeQuery();
                
                if(rs.next()){
                    cli1.setIdcl(rs.getInt("idcl"));
                    cli1.setNombre(rs.getString("nombre"));
                    cli1.setApellido(rs.getString("apellido"));
                    cli1.setCi(rs.getString("ci"));
                    cli1.setCelular(rs.getString("celular"));
                }
                request.setAttribute("cli", cli1);
                request.getRequestDispatcher("editarcliente.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(op.equals("eliminar")){
            //operaciones para eliminar un registro
            idcl = Integer.parseInt(request.getParameter("idcl")); //estamos recbiendo el valor del id para eliminar
            try {                
                ps = conn.prepareStatement("delete from cliente where idcl = ? ");
                ps.setInt(1, idcl);
                
                ps.executeUpdate();
                response.sendRedirect("MainControllerClientes");
                        } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idcl = Integer.parseInt(request.getParameter(("idcl"))); //recuperamos el valor del id
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String ci = request.getParameter("ci");
        String celular = request.getParameter("celular");
        
        Cliente cli = new Cliente();
        cli.setIdcl(idcl);
        cli.setNombre(nombre);
        cli.setApellido(apellido);
        cli.setCi(ci);
        cli.setCelular(celular);
        
        ConexionDB canal = new ConexionDB();
        Connection conn = canal.conectar();
        PreparedStatement ps;
        ResultSet rs;
        
        if(idcl == 0){
            //insertar registro
                String sql = "insert into cliente (idcl, nombre, apellido, ci, celular) values (?, ?, ?, ?, ?)";
            try {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, cli.getIdcl());
                ps.setString(2, cli.getNombre());
                ps.setString(3, cli.getApellido());
                ps.setString(4, cli.getCi());
                ps.setString(5, cli.getCelular());
                
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            //actualizar registro
                String sql1 = "update cliente set nombre=?, apellido=?, ci=?, celular=? where idcl=?";
            try {   
                ps = conn.prepareStatement(sql1);              
                ps.setString(1, cli.getNombre());
                ps.setString(2, cli.getApellido());
                ps.setString(3, cli.getCi());
                ps.setString(4, cli.getCelular());
                ps.setInt(5, cli.getIdcl());
                
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //lamada al index.jsp
        response.sendRedirect("MainControllerClientes");
    }
}
