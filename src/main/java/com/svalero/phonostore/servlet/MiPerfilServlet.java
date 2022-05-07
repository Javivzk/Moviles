package com.svalero.phonostore.servlet;

import com.svalero.phonostore.dao.ClienteDao;
import com.svalero.phonostore.dao.Database;
import com.svalero.phonostore.dao.UsuarioDao;
import com.svalero.phonostore.domain.Cliente;
import com.svalero.phonostore.domain.Usuario;
import com.svalero.phonostore.util.ValidaSesion;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/miPerfil")
public class MiPerfilServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        HttpSession sesion = req.getSession();
        String nombreUsuario = (String) sesion.getAttribute("nombre");
        String contrasenia = (String) sesion.getAttribute("password");
        String role = (String) sesion.getAttribute("role");

        //Usuario
        Integer idUsuario = Integer.parseInt(req.getParameter("id"));
        String nombre = req.getParameter("nombre");
        String apellido1 = req.getParameter("apellido1");
        String apellido2 = req.getParameter("apellido2");
        String telefono = req.getParameter("telefono");
        String email = req.getParameter("email");
        String contraseniaActual = req.getParameter("contraseniaActual");
        String contraseniaNueva = req.getParameter("contrasenia");
        //Cliente
        String direccion = req.getParameter("direccion");
        String provincia = req.getParameter("provincia");
        String codigoPostal = req.getParameter("postal");

        Usuario usuario = null;
        Cliente cliente = null;

        if (nombreUsuario == null || contrasenia == null || !ValidaSesion.validar(nombreUsuario, contrasenia)) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            dispatcher.forward(req, resp);
        } else {
            Database database = new Database();
            Connection connection = database.getConnection();
            if(role.equals("USER")){
                UsuarioDao usuarioDao = new UsuarioDao(connection);
                ClienteDao clienteDao = new ClienteDao(connection);
                try {
                    boolean correcto1, correcto2, correcto3;

                    connection.setAutoCommit(false);

                    usuario = new Usuario(idUsuario, nombreUsuario, contrasenia, nombre, apellido1, apellido2, telefono, email, null);
                    cliente = new Cliente(usuario, null, direccion, provincia, codigoPostal);

                    System.out.println(usuario);
                    System.out.println(cliente);

                    correcto1 = usuarioDao.modificaUsuario(usuario);
                    correcto2 = clienteDao.modificaCliente(cliente);

                    if(!contraseniaActual.equals("") || !contraseniaNueva.equals("")){
                        if(contrasenia.equals(contraseniaActual)){
                            correcto3 = usuarioDao.modificaUsuario("contraseña", contraseniaNueva, usuario.getIdUsuario());
                            sesion.setAttribute("password", contraseniaNueva);
                            out.println("<div class='alert alert-success' role='alert'>Modificado correctamente!</div>");
                        } else {
                            out.println("<div class='alert alert-danger' role='alert'>La contraseña es incorrecta</div>");
                        }
                    } else {
                        out.println("<div class='alert alert-success' role='alert'>Modificado correctamente!</div>");
                    }

                    connection.commit();
                    connection.setAutoCommit(true);

                } catch (SQLException sqle){
                    out.println("<div class='alert alert-danger' role='alert'>Ha habido un error con la base de datos</div>");
                } finally {
                    database.close();
                }

            } else {
                UsuarioDao usuarioDao = new UsuarioDao(connection);
                try {
                    boolean correcto1, correcto2;

                    connection.setAutoCommit(false);

                    usuario = new Usuario(idUsuario, nombreUsuario, contrasenia, nombre, apellido1, apellido2, telefono, email, null);

                    System.out.println(usuario);
                    System.out.println(contraseniaActual);

                    correcto1 = usuarioDao.modificaUsuario(usuario);

                    if(!contraseniaActual.equals("") || !contraseniaNueva.equals("")){
                        if(contrasenia.equals(contraseniaActual)){
                            correcto2 = usuarioDao.modificaUsuario("contraseña", contraseniaNueva, usuario.getIdUsuario());
                            sesion.setAttribute("password", contraseniaNueva);
                            out.println("<div class='alert alert-success' role='alert'>Modificado correctamente!</div>");
                        } else {
                            out.println("<div class='alert alert-danger' role='alert'>La contraseña es incorrecta</div>");
                        }
                    } else {
                        out.println("<div class='alert alert-success' role='alert'>Modificado correctamente!</div>");
                    }

                    connection.commit();
                    connection.setAutoCommit(true);

                } catch (SQLException sqle){
                    out.println("<div class='alert alert-danger' role='alert'>Ha habido un error con la base de datos</div>");
                } finally {
                    database.close();
                }
            }
        }
    }
}
