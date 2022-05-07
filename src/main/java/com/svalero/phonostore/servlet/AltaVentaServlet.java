package com.svalero.phonostore.servlet;

import com.svalero.phonostore.dao.Database;
import com.svalero.phonostore.dao.VentaDao;
import com.svalero.phonostore.domain.Venta;
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

@WebServlet("/altaVenta")
public class AltaVentaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        HttpSession sesion = req.getSession();
        String nombreUsuario = (String) sesion.getAttribute("nombre");
        String contrasenia = (String) sesion.getAttribute("password");

        String empleado = req.getParameter("empleado");
        String cliente = req.getParameter("cliente");
        String movil = req.getParameter("movil");
        String imei = req.getParameter("imei");
        String color = req.getParameter("color");
        Integer precio = Integer.parseInt(req.getParameter("precio"));
        System.out.println(empleado);
        System.out.println(cliente);
        System.out.println(movil);
        System.out.println(imei);
        System.out.println(color);
        System.out.println(precio);


        if(nombreUsuario == null || contrasenia == null || !ValidaSesion.validar(nombreUsuario, contrasenia)){
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            dispatcher.forward(req, resp);
        } else {
            Venta venta = new Venta(empleado, cliente, movil, imei, color, precio);
            Database database = new Database();
            Connection connection = database.getConnection();
            VentaDao ventaDao = new VentaDao(connection);
            try {
                ventaDao.altaVenta(venta);
                out.println("<div class='alert alert-success' role='alert'>Venta añadida correctamente!</div>");

            } catch (SQLException sqle) {
                out.println("<div class='alert alert-danger' role='alert'>Ha habido un error con la base de datos</div>");
                sqle.printStackTrace();
            } finally {
                database.close();
            }
        }
    }
}
