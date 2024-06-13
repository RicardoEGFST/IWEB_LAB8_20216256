

<%@page import="java.util.ArrayList"%>
<%@page import="com.example.pruebalaboratorio1.beans.pelicula"%>
<%@page import="com.example.pruebalaboratorio1.beans.genero"%>
<%@page import="java.text.NumberFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<pelicula> lista = (ArrayList<pelicula>) request.getAttribute("listaPeliculas");
    ArrayList<genero> listaGeneros = (ArrayList<genero>) request.getAttribute("listaGeneros");
    NumberFormat formatter = NumberFormat.getInstance();
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Películas</title>
</head>
<body>

<h1>Lista de Películas</h1>



<table border="1">
    <tr>
        <th>Título</th>
        <th>Director</th>
        <th>Año Publicación</th>
        <th>Rating</th>
        <th>Box Office</th>
        <th>Género</th>
        <th>Duración</th>
        <th>Actores</th>
        <th>Borrar</th>
    </tr>
    <%
        for (pelicula movie : lista) {
            String nombreGenero = (movie.getGen() != null) ? movie.getGen().getNombre() : "Sin género";
    %>
    <tr>
        <td><a href="viewPelicula?idPelicula=<%= movie.getIdPelicula() %>"><%= movie.getTitulo() %></a></td>
        <td><%= movie.getDirector() %></td>
        <td><%= movie.getAnoPublicacion() %></td>
        <td><%= movie.getRating() %>/10</td>
        <td>$<%= formatter.format(movie.getBoxOffice()) %></td>
        <td><%= nombreGenero %></td>
        <td><%= movie.getDuracion() %></td>
        <td><a href="listaActores?idPelicula=<%= movie.getIdPelicula() %>">Ver Actores</a></td>
        <td>
            <a href="listaPeliculas?action=borrar&idPelicula=<%= movie.getIdPelicula() %>" onclick="return confirm('¿Estás seguro de que deseas eliminar esta película?');">Borrar</a>
        </td>
    </tr>
    <%
        }
    %>
</table>

<a href="listaPeliculas?action=crear">Crear Película</a>

</body>
</html>
