<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.svalero.phonostore.dao.Database" %>
<%@ page import="com.svalero.phonostore.dao.MovilDao" %>
<%@ page import="com.svalero.phonostore.domain.Movil" %>
<%@ page import="com.svalero.phonostore.dao.VentaDao" %>
<%@ page import="com.svalero.phonostore.domain.Venta" %>
<%@ page import="com.svalero.phonostore.dao.EmpleadoDao" %>
<%@ page import="com.svalero.phonostore.domain.Empleado" %>
<%@ page import="com.svalero.phonostore.dao.ClienteDao" %>
<%@ page import="com.svalero.phonostore.domain.Cliente" %>

<!DOCTYPE html>
<html lang="es">

<%
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
    Database database = new Database();
    Connection connection = database.getConnection();
%>

    <head>
        <!-- Comentario -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Alta venta nueva</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>

    <body>
        <script type="text/javascript">
                $(document).ready(function() {
                    $("form").on("submit", function(event) {
                        event.preventDefault();
                        var formValue = $(this).serialize();
                        $.post("altaVenta", formValue, function(data) {
                            $("#result").html(data);
                        });
                    });
                });
        </script>
        <div class="container">
        <h2>
            <p class="text-center">Alta venta nueva</p>
        </h2>
            <form>
                <div class="form-group">
                    <label for="empleado">Selecciona el empleado que ha hecho la venta:</label>
                    <select class="form-control" id="empleado" name="empleado">
                        <%
                            EmpleadoDao empleadoDao = new EmpleadoDao(connection);
                            ArrayList<Empleado> empleados = empleadoDao.findAll();
                            for (Empleado emp : empleados) {
                                out.println("<option value=\"" + emp.getDni() + "\">" + emp.getNombre() + " " + emp.getApellidos1() + "</option>");
                            }
                        %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="cliente">Selecciona el cliente que hace la compra:</label>
                    <select class="form-control" id="cliente" name="cliente">
                        <%
                            ClienteDao clienteDao = new ClienteDao(connection);
                            ArrayList<Cliente> clientes = clienteDao.findAll();
                            for (Cliente cli : clientes) {
                                out.println("<option value=\"" + cli.getDni() + "\">" + cli.getNombre() + " " + cli.getApellidos1() + "</option>");
                            }
                        %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="movil">Selecciona el movil que va a comprar el cliente:</label>
                    <select class="form-control" id="movil" name="movil">
                         <%
                             MovilDao movilDao = new MovilDao(connection);
                             ArrayList<Movil> moviles = movilDao.findAll();
                             for (Movil mov : moviles) {
                                 out.println("<option value=\"" + mov.getReferencia() + "\">" + mov.getMarca() + " " + mov.getModelo() + "</option>");
                             }
                         %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="imei">Introduce el imei asignado:</label>
                    <input type="text" class="form-control" id="imei" name="imei" placeholder="128535487491666">
                </div>
                <div class="form-group">
                    <label for="color">Introduce el color:</label>
                    <input type="text" class="form-control" id="color" name="color" placeholder="Blanco">
                </div>
                <div class="form-group">
                    <label for="precio">Introduce el precio total:</label>
                    <input type="text" class="form-control" id="precio" name="precio" placeholder="23000">
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Enviar</button>
                    <button type="button" onclick="window.location.href='index.jsp'" class="btn btn-danger">Volver</button>
                </div>
                <div id="result"></div>
            </form>
        </div>
    </body>

</html>