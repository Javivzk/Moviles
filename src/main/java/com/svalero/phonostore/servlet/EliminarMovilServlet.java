package com.svalero.phonostore.servlet;

import com.svalero.phonostore.dao.Database;
import com.svalero.phonostore.dao.MovilDao;
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

@WebServlet("/eliminar-movil")
public class EliminarMovilServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        HttpSession sesion = req.getSession();
        String nombreUsuario = (String) sesion.getAttribute("nombre");
        String contrasenia = (String) sesion.getAttribute("password");

        String referencia = req.getParameter("referencia");

        if(nombreUsuario == null || contrasenia == null || !ValidaSesion.validar(nombreUsuario, contrasenia)) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            dispatcher.forward(req, resp);
        }

        String id = req.getParameter("id");

        Database database = new Database();
        MovilDao movilDao = new MovilDao(database.getConnection());
        try {
            movilDao.eliminarMovil(id);
            out.println("<div class='alert alert-success' role='alert'>El movil se ha borrado</div>");
        } catch (SQLException sqle) {
            out.println("<div class='alert alert-danger' role='alert'>Se ha producido un error al borrar el movil</div>");
            sqle.printStackTrace();
        }
        // TODO Eliminar el libro cuyo id se para como parametro

        }
    }
