package com.example.pruebalaboratorio1.daos;

import com.example.pruebalaboratorio1.beans.genero;
import com.example.pruebalaboratorio1.beans.pelicula;

import java.sql.*;
import java.util.ArrayList;

public class peliculaDao {

    public ArrayList<pelicula> listarPeliculas() {

        ArrayList<pelicula> listaPeliculas = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=America/Lima";
        String username = "root";
        String password = "PoCoYo137F";


        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();


            String sql = "SELECT p.idPelicula, p.titulo, p.director, p.anoPublicacion, p.rating, p.boxOffice, " +
                    "g.idGenero, g.nombre AS generoNombre, p.duracion " +
                    "FROM pelicula p " +
                    "LEFT JOIN genero g ON p.idGenero = g.idGenero";



            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                pelicula movie = new pelicula();
                int idPelicula = rs.getInt(1);
                movie.setIdPelicula(idPelicula);
                String titulo = rs.getString("titulo");
                movie.setTitulo(titulo);
                String director = rs.getString("director");
                movie.setDirector(director);
                int anoPublicacion = rs.getInt("anoPublicacion");
                movie.setAnoPublicacion(anoPublicacion);
                double rating = rs.getDouble("rating");
                movie.setRating(rating);
                double boxOffice = rs.getDouble("boxOffice");
                movie.setBoxOffice(boxOffice);

                genero gener = new genero();
                gener.setIdGenero(rs.getInt("idGenero"));
                gener.setNombre(rs.getString("generoNombre"));
                movie.setGen(gener);
                String duracion = rs.getString("duracion");
                movie.setDuracion(duracion);
                /*String streaming = rs.getString("nombreServicio");
                movie.setStreaming(streaming);
                boolean oscar = rs.getBoolean("premioOscar");
                movie.setPremioOscar(oscar);*/

                listaPeliculas.add(movie);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaPeliculas;
    }

    public ArrayList<pelicula> listarPeliculasFiltradas(int idGenero) {

        ArrayList<pelicula> listaPeliculasFiltradas= new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=America/Lima";
        String username = "root";
        String password = "PoCoYo137F";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT p.idPelicula, p.titulo, p.director, p.anoPublicacion, p.rating, p.boxOffice, " +
                             "g.idGenero, g.nombre AS generoNombre " +
                             "FROM pelicula p " +
                             "LEFT JOIN genero g ON p.idGenero = g.idGenero " +
                             "WHERE g.idGenero = ?")) {

            stmt.setInt(1, idGenero);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                pelicula movie = new pelicula();
                int idPelicula = rs.getInt(1);
                movie.setIdPelicula(idPelicula);
                String titulo = rs.getString("titulo");
                movie.setTitulo(titulo);
                String director = rs.getString("director");
                movie.setDirector(director);
                int anoPublicacion = rs.getInt("anoPublicacion");
                movie.setAnoPublicacion(anoPublicacion);
                double rating = rs.getDouble("rating");
                movie.setRating(rating);
                double boxOffice = rs.getDouble("boxOffice");
                movie.setBoxOffice(boxOffice);


                //Parte de la extensión del bean

                genero gener = new genero();
                gener.setIdGenero(rs.getInt("idGenero"));
                gener.setNombre(rs.getString("generoNombre"));
                movie.setGen(gener);

                listaPeliculasFiltradas.add(movie);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPeliculasFiltradas;
    }


    public void crearPelicula( ){

    }


    public void borrarPelicula(int idPelicula) {
        String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=America/Lima";
        String username = "root";
        String password = "PoCoYo137F";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            // Primero eliminar las filas relacionadas en `protagonistas`
            String delProtagonistas = "DELETE FROM protagonistas WHERE idPelicula = ?";
            try (PreparedStatement pstmtDelProtagonistas = conn.prepareStatement(delProtagonistas)) {
                pstmtDelProtagonistas.setInt(1, idPelicula);
                pstmtDelProtagonistas.executeUpdate();
            }

            // Luego eliminar la fila en `pelicula`
            String delPeli = "DELETE FROM pelicula WHERE idPelicula = ?";
            try (PreparedStatement pstmtDelPeli = conn.prepareStatement(delPeli)) {
                pstmtDelPeli.setInt(1, idPelicula);
                pstmtDelPeli.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
