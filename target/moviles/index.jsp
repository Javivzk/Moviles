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
        <title>Phono Store</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    </head>

    <body>
        <div class="container">
            <h2>
                <p class="text-center">Bienvenido a Phono Store</p>
            </h2>
            <div class="container">
                <p> Bienvenido
                    <%
                    out.print("<a href=\"miPerfil.jsp\" class=\"link-primary\">");
                    out.print(n);
                    out.print("</a>");
                %>
                        <form action="logout" method="post">
                            <input type="submit" name="logout" value="Cerrar sesion" />
                        </form>
                </p>
            </div>
            <div class="list-group">
                <a href="buscaMovil.jsp" class="list-group-item list-group-item-action">Buscar moviles</a>
                <a href="verMoviles.jsp" class="list-group-item list-group-item-action">Ver todos los moviles</a>
                <a href="verVentas.jsp" class="list-group-item list-group-item-action">Ver todas las ventas</a>
                <%
                    if((role != null) && (role.equals("ADMIN") || role.equals("EMPLOYEE"))){
                %>
                <a href="altaMovil.jsp" class="list-group-item list-group-item-action">A&ntilde;adir movil nuevo</a>
                <a href="buscaVenta.jsp" class="list-group-item list-group-item-action">Buscar una venta</a>
                <a href="altaVenta.jsp" class="list-group-item list-group-item-action">Nueva venta</a>
                <%
                    }
                %>
            </div>
        </div>
    </body>

</html>