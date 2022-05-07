<%@ page import="com.svalero.phonostore.dao.ClienteDao" %>
<%@ page import="com.svalero.phonostore.domain.Cliente" %>
<%@ page import="com.svalero.phonostore.dao.UsuarioDao" %>
<%@ page import="com.svalero.phonostore.domain.Usuario" %>
<%@ page import="com.svalero.phonostore.util.CogeUsuarioCliente" %>
<%@ page import="com.svalero.phonostore.util.ValidaSesion" %>
<%@ page import="com.svalero.phonostore.dao.Database" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Connection" %>

<!DOCTYPE html>
<html lang="es">

<%
    String nombre=(String)session.getAttribute("nombre");
    String contrasenia=(String)session.getAttribute("password");
    String role=(String)session.getAttribute("role");
    Cliente cliente = null;
    Usuario usuario = null;
    if(nombre == null){
        String redirectURL = "login.jsp";
        response.sendRedirect(redirectURL);
    } else {
        Database database = new Database();
        Connection connection = database.getConnection();
        if(!ValidaSesion.validar(nombre, contrasenia)){
            String redirectURL = "login.jsp";
            response.sendRedirect(redirectURL);
        } else {
            if(role.equals("USER")){
                //Usuario y cliente
                usuario = CogeUsuarioCliente.getUsuario(nombre, contrasenia, connection);
                cliente = CogeUsuarioCliente.getCliente(usuario, connection);
                System.out.println(usuario);
                System.out.println(cliente);
                database.close();
            } else {
                usuario = CogeUsuarioCliente.getUsuario(nombre, contrasenia, connection);
                System.out.println(usuario);
                database.close();
            }
        }
    }
%>

<head>
    <!-- Comentario -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mi perfil</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>

<body>
    <script type="text/javascript">
        $(document).ready(function() {
            $("form").on("submit", function(event) {
                event.preventDefault();
                var formValue = $(this).serialize();
                $.post("miPerfil", formValue, function(data) {
                    $("#result").html(data);
                });
                $('#envia').button('dispose')
            });
        });
    </script>
    <div class="container">
        <h2>
            <p class="text-center">Mi perfil</p>
        </h2>
        <form action="miPerfil" method="post">
            <div class="form-group">
                <label for="nombreUsuario">Nombre de usuario</label>
                <input type="text" readonly class="form-control-plaintext" id="nombreUsuario" name="nombreUsuario" <% if(usuario!=null){out.println("value=\"" + usuario.getNombreUsuario() + "\"");} %>>
            </div>
            <div class="form-group">
                            <label for="nombre">Nombre</label>
                            <input type="text" class="form-control" id="nombre" name="nombre" <% if(usuario!=null){out.println("value=\"" + usuario.getNombre() + "\"");} %>>
                        </div>
            <div class="form-group">
                            <label for="apellido1">Primer apellido</label>
                            <input type="text" class="form-control" id="apellido1" name="apellido1" <% if(usuario!=null){out.println("value=\"" + usuario.getApellidos1() + "\"");} %>>
                        </div>
            <div class="form-group">
                            <label for="apellido2">Segundo apellido</label>
                            <input type="text" class="form-control" id="apellido2" name="apellido2" <% if(usuario!=null){out.println("value=\"" + usuario.getApellidos2() + "\"");} %>>
                        </div>
            <div class="form-group">
                <label for="telefono">Telefono</label>
                <input type="text" class="form-control" id="telefono" name="telefono" <% if(usuario!=null){out.println("value=\"" + usuario.getTelefono() + "\"");} %>>
            </div>
            <div class="form-group">
                 <label for="email">Email</label>
                 <input type="text" class="form-control" id="email" name="email" <% if(usuario!=null){out.println("value=\"" + usuario.getEmail() + "\"");} %>>
            </div>
            <div class="form-row">
                <div class="form-group col">
                    <label for="contraseniaActual">Contrase&ntilde;a actual</label>
                    <input type="text" class="form-control" id="contraseniaActual" name="contraseniaActual" placeholder="Nombre de usuario">
                </div>
                <div class="form-group col">
                    <label for="contrasenia">Contrase&ntilde;a nueva</label>
                    <input type="text" class="form-control" id="contrasenia" name="contrasenia" placeholder="Nombre de usuario">
                </div>
            </div>
            <!-- Cambios de cliente -->
            <%
                if(role != null){
                    if(role.equals("USER")){
                        out.println("<div class=\"form-group\">");
                        out.println("<label for=\"direccion\">Direccion</label>");
                        out.println("<input type=\"text\" class=\"form-control\" id=\"direccion\" name=\"direccion\" value=\"" + cliente.getDireccion() + "\">");
                        out.println("</div>");
                        out.println("<div class=\"form-group\">");
                        out.println("<label for=\"provincia\">Provincia</label>");
                        out.println("<input type=\"text\" class=\"form-control\" id=\"provincia\" name=\"provincia\" value=\"" + cliente.getProvincia() + "\">");
                        out.println("</div>");
                        out.println("<div class=\"form-group\">");
                        out.println("<label for=\"postal\">Codigo Postal</label>");
                        out.println("<input type=\"text\" class=\"form-control\" id=\"postal\" name=\"postal\" value=\"" + cliente.getCodigoPostal() + "\">");
                        out.println("</div>");
                    }
                }
            %>
            <input type="hidden" class="form-control" id="id" name="id" <% if(usuario!=null){out.println("value=\"" + usuario.getIdUsuario() + "\"");} %>>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Enviar</button>
            </div>
            <div id="result"></div>
        </form>
    </div>
</body>

</html>