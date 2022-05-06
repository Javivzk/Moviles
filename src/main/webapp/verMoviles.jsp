<%@ page import="com.svalero.phonostore.dao.Database" %>
<%@ page import="com.svalero.phonostore.dao.MovilDao" %>
<%@ page import="com.svalero.phonostore.domain.Movil" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html lang="es">
    <%
        String n=(String)session.getAttribute("nombre");
        String role=(String)session.getAttribute("role");
        if(n == null){
            String redirectURL = "login.jsp";
            response.sendRedirect(redirectURL);
        }
    %>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Ver todos los moviles</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    </head>

    <body>
        <%
            Database database = new Database();
            MovilDao movilDao = new MovilDao(database.getConnection());
        %>
        <div class="container">
            <h2>
                <p class="text-center">Lista de todos los moviles</p>
            </h2>
            <div class="container">
                <%
                    try {
                        out.println("<ul class='list-group'>");
                        ArrayList<Movil> moviles = movilDao.findAll();
                        for (Movil movil : moviles) {
                            out.println("<li class='list-group-item d-flex justify-content-between '>" + movil.toString());
                                if((role != null) && (role.equals("ADMIN") || role.equals("EMPLOYEE"))){

                                    out.println("<a class=\"btn btn-success\" href=\"movil.jsp?id=" + movil.getReferencia() + "\" role=\"button\">Detalles</a>");
                                    out.println("<a class=\"btn btn-warning\" href=\"altaMovil.jsp?id=" + movil.getReferencia() + "\" role=\"button\">Modificar</a>");
                                    out.println("<a class=\"btn btn-danger\" href=\"eliminar-movil?id=" + movil.getReferencia() + "\" role=\"button\">Eliminar</a>");

                                }
                            out.println("</li>");
                        }
                        out.println("</ul>");
                    } catch (SQLException sqle) {
                        out.println("<h3>Ha habido un error al cargar los moviles</h3>");
                    }
                %>
            </div>
        </div>
    </body>
</html>