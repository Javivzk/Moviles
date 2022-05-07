<%@ page import="com.svalero.phonostore.dao.Database" %>
<%@ page import="com.svalero.phonostore.dao.MovilDao" %>
<%@ page import="com.svalero.phonostore.domain.Movil" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.List" %>
<%@ page import="com.svalero.phonostore.domain.Usuario" %>

    <%
        String n=(String)session.getAttribute("nombre");
        String role=(String)session.getAttribute("role");
        if(n == null){
            String redirectURL = "login.jsp";
            response.sendRedirect(redirectURL);
        }
    %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <h2>Listado de libros</h2>
        <ul class="list-group">
            <%
                // Acceder a la base de datos y recuperar la información de los libros
                Database database = new Database();
                MovilDao movilDao = new MovilDao(database.getConnection());
                try {
                    List<Movil> moviles = movilDao.findAll();
                    for (Movil movil : moviles) {
            %>
                        <li class="list-group-item">
                            <a target="_blank" href="movil.jsp?id=<%= movil.getReferencia() %>"><%= movil.getMarca() %> <%= movil.getModelo() %></a>
                            <%
                                if((role != null) && (role.equals("ADMIN") || role.equals("EMPLOYEE"))){
                            %>
                                    <a href="altaMovil.jsp?id=<%= movil.getReferencia() %>" class="btn btn-outline-warning">Modificar</a>
                                    <a href="eliminar-movil?id=<%= movil.getReferencia() %>" class="btn btn-outline-danger">Eliminar</a>
                            <%
                                }
                            %>
                        </li>
            <%
                    }
               } catch (SQLException sqle) {
            %>
                    <div class="alert alert-danger" role="alert">
                      Error conectando con la base de datos
                    </div>
            <%
               }
            %>
        </ul>
    </div>
</body>
</html>