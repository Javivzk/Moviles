package com.svalero.phonostore.servlet;

import com.svalero.phonostore.dao.Database;
import com.svalero.phonostore.dao.VentaDao;
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

@WebServlet("/bajaVenta")
public class BajaVentaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        HttpSession sesion = req.getSession();
        String nombreUsuario = (String) sesion.getAttribute("nombre");
        String contrasenia = (String) sesion.getAttribute("password");

        String id = req.getParameter("id");

        if(nombreUsuario == null || contrasenia == null || !ValidaSesion.validar(nombreUsuario, contrasenia)){
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            dispatcher.forward(req, resp);
        } else {
            Database database = new Database();
            Connection connection = database.getConnection();
            boolean correcto;

            try {
                VentaDao ventaDao = new VentaDao(connection);
                correcto = ventaDao.borraVenta(Integer.parseInt(id));
                if(correcto){
                    out.println("<div class='alert alert-success' role='alert'>Venta eliminada correctamente!</div>");
                } else {
                    out.println("<div class='alert alert-danger' role='alert'>No se ha podido eliminar la venta</div>");
                }
            } catch (SQLException sqle) {
                out.println("<div class='alert alert-danger' role='alert'>Ha habido un error con la base de datos</div>");
            }
        }
    }
}
