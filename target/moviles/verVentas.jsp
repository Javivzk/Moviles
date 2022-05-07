<%@ page import="com.svalero.phonostore.dao.Database" %>
<%@ page import="com.svalero.phonostore.dao.MovilDao" %>
<%@ page import="com.svalero.phonostore.domain.Movil" %>
<%@ page import="com.svalero.phonostore.dao.VentaDao" %>
<%@ page import="com.svalero.phonostore.domain.Venta" %>
<%@ page import="com.svalero.phonostore.dao.EmpleadoDao" %>
<%@ page import="com.svalero.phonostore.domain.Empleado" %>
<%@ page import="com.svalero.phonostore.dao.ClienteDao" %>
<%@ page import="com.svalero.phonostore.domain.Cliente" %>
<%@ page import="com.svalero.phonostore.dao.UsuarioDao" %>
<%@ page import="com.svalero.phonostore.domain.Usuario" %>
<%@ page import="com.svalero.phonostore.util.CogeUsuarioCliente" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Connection" %>


<!DOCTYPE html>
<html lang="es">
    <%
        String n=(String)session.getAttribute("nombre");
        String contrasenia = (String) session.getAttribute("password");
        String role=(String)session.getAttribute("role");
        if(n == null){
            String redirectURL = "login.jsp";
            response.sendRedirect(redirectURL);
        }
    %>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Ver ventas</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    </head>

    <body>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <%
            Database database = new Database();
            Connection connection = database.getConnection();
            Venta venta;
            Empleado empleado;
            Cliente cliente;
            Movil movil;
        %>
        <div class="container">
            <h2>
                <p class="text-center">Lista de todas las ventas</p>
            </h2>
            <div class="container">
                <%
                    try {
                    out.println("<div id=\"accordion\">");
                        ArrayList<Venta> ventas = new ArrayList<Venta>();
                        VentaDao ventaDao = new VentaDao(connection);
                        Cliente cliente1 = null;
                        Usuario usuario = null;
                        usuario = CogeUsuarioCliente.getUsuario(n, contrasenia, connection);
                        if(usuario != null){
                            cliente1 = CogeUsuarioCliente.getCliente(usuario, connection);
                            System.out.println("Pasa por aqui");
                            System.out.println(cliente1);
                        }
                        if((role != null) && (role.equals("ADMIN") || role.equals("EMPLOYEE"))){
                            ventas = ventaDao.findAll();
                        } else {
                            System.out.println("Pasa por aqui 1");
                            if(cliente1 != null){
                                System.out.println("Pasa por aqui 2");
                                String dniCliente = cliente1.getDni();
                                ventas = ventaDao.findVenta(dniCliente);
                            }
                        }
                        Integer id = 1;
                        for (Venta aux : ventas) {
                                out.print("<div class=\"card\"><div class=\"card-header\" id=\"heading" + id + "\"><h5 class=\"mb-0\"><button class=\"btn btn-link collapsed\" data-toggle=\"collapse\" data-target=\"#collapse" + id + "\" aria-expanded=\"false\" aria-controls=\"collapse" + id + "\">" + aux.toString() + "</button></h5></div>");

                                ClienteDao clienteDao = new ClienteDao(connection);
                                EmpleadoDao empleadoDao = new EmpleadoDao(connection);
                                MovilDao movilDao = new MovilDao(connection);

                                cliente = clienteDao.getCliente(aux.getDni_cliente());
                                empleado = empleadoDao.getEmpleado(aux.getDni_empleado());
                                movil = movilDao.getMovil(aux.getReferencia());

                                out.print("<div id=\"collapse" + id + "\" class=\"collapse\" aria-labelledby=\"heading" + id + "\" data-parent=\"#accordion\"> <div class=\"card-body\">");
                                out.println("<ul class=\"list-group list-group-flush\">");
                                out.print("<li class=\"list-group-item\">");
                                out.print(cliente.toString());
                                out.println("</li>");
                                out.print("<li class=\"list-group-item\">");
                                out.println(empleado.toString());
                                out.println("</li>");
                                out.print("<li class=\"list-group-item\">");
                                out.println(movil.toString());
                                out.println("</li>");
                                out.println("</ul>");
                                if((role != null) && (role.equals("ADMIN") || role.equals("EMPLOYEE"))){
                                    out.print("<a class=\"btn btn-danger\" href=\"confirmaEliminar.jsp?id=" + aux.getIdVenta() + "\" role=\"button\">Eliminar</a>");
                                }
                                out.println("</div></div></div>");
                                id++;
                        }
                    } catch (SQLException sqle) {
                        out.println("<h3>Ha habido un error al cargar los moviles</h3>");
                    } finally {
                        database.close();
                    }
                    out.print("</div>");
                %>
            </div>
        </div>
    </body>
</html>