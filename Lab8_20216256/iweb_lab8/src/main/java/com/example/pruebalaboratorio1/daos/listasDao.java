package com.example.pruebalaboratorio1.daos;

import com.example.pruebalaboratorio1.beans.genero;
import com.example.pruebalaboratorio1.beans.streaming;
import com.example.pruebalaboratorio1.beans.pelicula;

import java.sql.*;
import java.util.ArrayList;

public class listasDao extends baseDao{


    public ArrayList<genero> listarGeneros() {
        ArrayList<genero> listaGeneros = new ArrayList<>();


        String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=America/Lima";
        String username = "root";
        String password = "PoCoYo137F";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement()) {

            String sql = "SELECT * FROM genero";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                genero gen = new genero();
                gen.setIdGenero(rs.getInt(1));
                gen.setNombre(rs.getString(2));
                listaGeneros.add(gen);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaGeneros;
    }
}
