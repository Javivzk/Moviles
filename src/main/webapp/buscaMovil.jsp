<%@ page import="com.svalero.phonostore.dao.Database" %>
<%@ page import="com.svalero.phonostore.dao.MovilDao" %>
<%@ page import="com.svalero.phonostore.domain.Movil" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html lang="es">

<%
    String n=(String)session.getAttribute("nombre");
    if(n == null){
        String redirectURL = "login.jsp";
        response.sendRedirect(redirectURL);
    }
%>

    <head>
        <!-- Comentario -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Buscar moviles</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </head>

    <body>
        <script type="text/javascript">
                $(document).ready(function() {
                    $("form").on("submit", function(event) {
                        event.preventDefault();
                        var formValue = $(this).serialize();
                        $.post("buscaMovil", formValue, function(data) {
                            $("#result").html(data);
                        });
                    });
                });
        </script>
        <div class="container">
            <h2>
                <p class="text-center">Busqueda de moviles por modelo</p>
            </h2>
            <form>
                <div class="form-group">
                    <label for="modelo">Modelo a buscar:</label>
                    <input type="text" class="form-control" id="modelo" name="modelo" placeholder="Galaxy S10">
                </div>
                <div class="form-group">
                    <label for="marca">(Opcional) Selecciona una marca:</label>
                    <select class="form-control" id="marca" name="marca">
                        <option value="Todos">Todos</option>
                        <%
                            Database database = new Database();
                            MovilDao movilDao = new MovilDao(database.getConnection());
                            ArrayList<String> marcas = movilDao.getMarcas();
                            for (String str : marcas) {
                                out.println("<option value=\"" + str + "\">" + str + "</option>");
                            }
                        %>
                    </select>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Enviar</button>
                    <button type="button" onclick="window.location.href='index.jsp'" class="btn btn-danger">Volver</button>
                </div>
            </form>
            <div id="result"></div>
        </div>
    </body>
</html>