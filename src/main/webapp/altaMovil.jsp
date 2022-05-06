<%@ page import="com.svalero.phonostore.dao.Database" %>
<%@ page import="com.svalero.phonostore.dao.MovilDao" %>
<%@ page import="com.svalero.phonostore.domain.Movil" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>

<!DOCTYPE html>
<html lang="es">

<%
    String texto = "Enviar";
    String color = "primary";
    String parametro = request.getParameter("id");
    Boolean modifica = false;
    Movil movil = null;
    String n=(String)session.getAttribute("nombre");
    String role=(String)session.getAttribute("role");
    if(n == null){
        String redirectURL = "login.jsp";
        response.sendRedirect(redirectURL);
    }
    if((role != null) && !(role.equals("ADMIN") || role.equals("EMPLOYEE"))){
        String redirectURL = "index.jsp";
        response.sendRedirect(redirectURL);
    }
    if(parametro != null){
        texto = "Modificar";
        color = "warning";
        modifica = true;
        Database database = new Database();
        Connection connection = database.getConnection();
        MovilDao movilDao = new MovilDao(connection);

        try {
            movil = movilDao.getMovil(parametro);
        } catch (SQLException sqle) {
            out.println("<div class='alert alert-danger' role='alert'>Parametro incorrecto</div>");
        } finally {
            database.close();
        }
    }
%>

    <head>
        <!-- Comentario -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Alta movil nuevo</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>

    <body>
        <script type="text/javascript">
                $(document).ready(function() {
                    $("form").on("submit", function(event) {
                        event.preventDefault();
                        var formValue = $(this).serialize();
                        $.post("altaMovil", formValue, function(data) {
                            $("#result").html(data);
                        });
                    });
                });
        </script>
        <div class="container">
            <h2>
                <p class="text-center">Registrar movil nuevo</p>
            </h2>
            <form>
                <div class="form-group">
                    <label for="marca">Introduce la marca:</label>
                    <input type="text" class="form-control" id="marca" name="marca" <% if(movil!=null){out.println("value=\"" + movil.getMarca() + "\"");} %> placeholder="Apple">
                </div>
                <div class="form-group">
                    <label for="modelo">Introduce el modelo:</label>
                    <input type="text" class="form-control" id="modelo" name="modelo" <% if(movil!=null){out.println("value=\"" + movil.getModelo() + "\"");} %> placeholder="Iphone XR">
                </div>
                <div class="form-group">
                    <label for="plazas">Introduce el color:</label>
                    <input type="text" class="form-control" id="color" name="color" <% if(movil!=null){out.println("value=\"" + movil.getColor() + "\"");} %> placeholder="Negro">
                </div>
                <div class="form-group">
                    <label for="precio">Introduce el precio:</label>
                    <input type="text" class="form-control" id="precio" name="precio" <% if(movil!=null){out.println("value=\"" + movil.getPrecioBase() + "\"");} %> placeholder="20000">
                    <input type="hidden" class="form-control" id="referencia" name="referencia" value="<%=parametro%>">
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-<%=color%>"><%=texto%></button>
                    <button type="button" onclick="window.location.href='index.jsp'" class="btn btn-danger">Volver</button>
                </div>
                <div id="result"></div>
            </form>
        </div>
    </body>

</html>