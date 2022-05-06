package com.svalero.phonostore.servlet;

import com.svalero.phonostore.dao.Database;
import com.svalero.phonostore.dao.MovilDao;
import com.svalero.phonostore.domain.Movil;
import com.svalero.phonostore.exception.YaExisteMovil;
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
import java.sql.SQLException;

@WebServlet("/altaMovil")
public class AltaMovilServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        HttpSession sesion = req.getSession();
        String nombreUsuario = (String) sesion.getAttribute("nombre");
        String contrasenia = (String) sesion.getAttribute("password");

        String marca = req.getParameter("marca");
        String modelo = req.getParameter("modelo");
        String color = req.getParameter("color");
        Integer precio = Integer.parseInt(req.getParameter("precio"));
        String referencia = req.getParameter("referencia");

        if(nombreUsuario == null || contrasenia == null || !ValidaSesion.validar(nombreUsuario, contrasenia)){
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            dispatcher.forward(req, resp);
        } else {
            Database database = new Database();
            MovilDao movilDao = new MovilDao(database.getConnection());
            try {
                System.out.println(referencia);
                if(!referencia.equals("null")){
                    Movil movil = new Movil(referencia.trim(), marca.trim(), modelo.trim(), color, precio);
                    boolean correcto = movilDao.modificaMovil(movil);
                    if (correcto) {
                        out.println("<div class='alert alert-success' role='alert'>El movil se ha modificado correctamente</div>");
                    } else {
                        out.println("<div class='alert alert-danger' role='alert'>No se ha podido modificar el movil</div>");
                    }
                } else {
                    Movil movil = new Movil(marca.trim(), modelo.trim(), color, precio);
                    movilDao.altaMovil(movil);
                    out.println("<div class='alert alert-success' role='alert'>El movil se ha añadido correctamente</div>");
                }
            } catch (YaExisteMovil yem) {
                database.close();
                out.println("<div class='alert alert-danger' role='alert'>El movil ya existe</div>");
            } catch (SQLException sqle) {
                database.close();
                out.println("<div class='alert alert-danger' role='alert'>Se ha producido un error</div>");
            }
        }

    }
}
