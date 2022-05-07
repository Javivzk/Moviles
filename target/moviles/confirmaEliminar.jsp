<%@ page import="com.svalero.phonostore.dao.Database" %>
<%@ page import="com.svalero.phonostore.dao.VentaDao" %>
<%@ page import="com.svalero.phonostore.domain.Venta" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html lang="es">

<%
    String n=(String)session.getAttribute("nombre");
    if(n == null){
        String redirectURL = "index.jsp";
        response.sendRedirect(redirectURL);
    }
%>

<head>
    <!-- Comentario -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Concesionario</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>

<body>
    <%
        Database database = new Database();
        Connection connection = database.getConnection();
        VentaDao ventaDao = new VentaDao(connection);
        //ArrayList<Venta> ventas = ventaDao.findByID(Integer.parseInt(request.getParam("id"));
        Venta venta = ventaDao.findById(Integer.parseInt(request.getParameter("id"))).get(0);

    %>
    <script type="text/javascript">
                    $(document).ready(function() {
                        $("form").on("submit", function(event) {
                            event.preventDefault();
                            var formValue = $(this).serialize();
                            $.post("bajaVenta", formValue, function(data) {
                                $("#result").html(data);
                            });
                            $('#envia').button('dispose')
                        });
                    });
            </script>
    <div class="container">
        <form action="bajaVenta" method="post">
            <div class="form-group">
                <label for="nombre">Estas seguro que quieres eliminar la venta:</label>
                <input type="hidden" class="form-control" id="id" name="id" <% out.println("value=\"" + request.getParameter("id") + "\""); %>>
            </div>

            <div class="form-group">
                <button type="submit" id="envia" class="btn btn-danger">Si</button>
                <button type="button" onclick="window.location.href='index.jsp'" class="btn btn-success">Volver</button>
            </div>
            <div id="result"></div>
        </form>
    </div>
</body>

</html>