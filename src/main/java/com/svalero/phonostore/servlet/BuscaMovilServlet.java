package com.svalero.phonostore.servlet;

import com.svalero.phonostore.dao.Database;
import com.svalero.phonostore.dao.MovilDao;
import com.svalero.phonostore.domain.Movil;
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
import java.util.ArrayList;

@WebServlet("/buscaMovil")
public class BuscaMovilServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        HttpSession sesion = req.getSession();
        String nombreUsuario = (String) sesion.getAttribute("nombre");
        String contrasenia = (String) sesion.getAttribute("password");

        String marca = req.getParameter("marca");
        String modelo = req.getParameter("modelo");

        if (nombreUsuario == null || contrasenia == null || !ValidaSesion.validar(nombreUsuario, contrasenia)) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            dispatcher.forward(req, resp);
        } else {
            Database database = new Database();
            Connection connection = database.getConnection();
            MovilDao movilDao = new MovilDao(connection);
            ArrayList<Movil> moviles;
            if(marca.equals("Todos")){
                try {
                    moviles=movilDao.findByModelo(modelo);
                    if(moviles.isEmpty()){
                        out.println("<div class=\"alert alert-warning\" role=\"alert\">No se han encontrado moviles con ese modelo</div>");
                    } else {
                        out.println("<ul class='list-group'>");
                        for (Movil movil : moviles) {
                            out.println("<li class='list-group-item'>" + movil.toString() + "</a></li>");
                        }
                        out.println("</ul>");
                    }
                }  catch (SQLException sqle) {
                    out.println("<div class='alert alert-danger' role='alert'>Ha habido un error con la base de datos</div>");
                } finally {
                    database.close();
                }
            } else {
                try {
                    moviles=movilDao.findByMarcaModelo(marca,modelo);
                    if(moviles.isEmpty()){
                        out.println("<div class=\"alert alert-warning\" role=\"alert\">No se han encontrado moviles con esa marca y modelo</div>");
                    } else {
                        out.println("<ul class='list-group'>");
                        for (Movil movil : moviles) {
                            out.println("<li class='list-group-item'>" + movil.toString() + "</a></li>");
                        }
                        out.println("</ul>");
                    }
                }  catch (SQLException sqle) {
                    out.println("<div class='alert alert-danger' role='alert'>Ha habido un error con la base de datos</div>");
                } finally {
                    database.close();
                }
            }
        }
    }
}
