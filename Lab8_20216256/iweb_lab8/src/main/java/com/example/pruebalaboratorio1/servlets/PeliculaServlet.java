package com.example.pruebalaboratorio1.servlets;

import com.example.pruebalaboratorio1.beans.genero;
import com.example.pruebalaboratorio1.beans.pelicula;
import com.example.pruebalaboratorio1.beans.streaming;
import com.example.pruebalaboratorio1.daos.listasDao;
import com.example.pruebalaboratorio1.daos.peliculaDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "pelicula-servlet", value = "/listaPeliculas")
public class PeliculaServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String action = request.getParameter("action");
        peliculaDao peliculaDao = new peliculaDao();
        listasDao listaDao = new listasDao();
        //ArrayList<genero> listarGeneros = listaDao.listarGeneros();
        //ArrayList<streaming> listarStraming = listaDao.listarStraming();

        switch (action) {
            case "listar":

                ArrayList<pelicula> listaPeliculas = peliculaDao.listarPeliculas();
                request.setAttribute("listaPeliculas", listaPeliculas);

                RequestDispatcher view = request.getRequestDispatcher("listaPeliculas.jsp");
                view.forward(request,response);
                break;

            case "filtrar":


                int idGenero = Integer.parseInt(request.getParameter("idGenero"));
                ArrayList<pelicula> peliculasFiltradas = (idGenero == 0) ? peliculaDao.listarPeliculas() : peliculaDao.listarPeliculasFiltradas(idGenero);
                request.setAttribute("listaPeliculas", peliculasFiltradas);
                RequestDispatcher viewFiltrar = request.getRequestDispatcher("listaPeliculas.jsp");
                viewFiltrar.forward(request, response);

                break;

            case "crear":
                RequestDispatcher view2 = request.getRequestDispatcher("crearPelicula.jsp");
                view2.forward(request,response);
                break;


            case "borrar":
                int idPelicula = Integer.parseInt(request.getParameter("idPelicula"));
                peliculaDao.borrarPelicula(idPelicula);
                response.sendRedirect(request.getContextPath()+"/listaPeliculas?action=listar");
                break;

        }


    }


}
