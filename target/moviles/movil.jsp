<%@ page import="com.svalero.phonostore.dao.Database" %>
<%@ page import="com.svalero.phonostore.dao.MovilDao" %>
<%@ page import="com.svalero.phonostore.domain.Movil" %>
<%@ page import="java.util.Optional" %>
<%@ page import="java.sql.SQLException" %>

<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
    <%
        String movilId = request.getParameter("id");
        Database db = new Database();
        MovilDao movilDao = new MovilDao(db.getConnection());
        Movil movil = null;
        try {
            Optional<Movil> optionalMovil = movilDao.findById(movilId);
            movil = optionalMovil.get();

    %>
    <div class="container">
        <div class="card text-center">
          <div class="card-header">
            Detalles del movil
          </div>
          <div class="card-body">
            <h5 class="card-title"><%= movil.getModelo() %></h5>
            <p class="card-text">Lanzado por <strong><%= movil.getMarca() %></strong></p>
            <a href="buy?id=<%= movil.getReferencia() %>" class="btn btn-primary">Comprar</a>
          </div>
          <div class="card-footer text-muted">
            Disponible en color: <strong><%= movil.getColor() %></strong>
            Por tan solo: <strong><%= movil.getPrecioBase() %>€</strong>
          </div>
        </div>
    </div>
    <%
        } catch (SQLException sqle) {
    %>
        <div class='alert alert-danger' role='alert'>Se ha producido al cargar los datos del movil</div>
    <%
        }
    %>
</body>
</html>
