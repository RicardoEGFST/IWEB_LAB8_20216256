package com.example.pruebalaboratorio1.beans;

public class pelicula {

    private int idPelicula;
    private String titulo;

    private String director;
    private int anoPublicacion;
    private Double rating;
    private double boxOffice;




    //Se cambia private String genero;
    private genero gen;



    //Se cambia private String streaming;
    private streaming stream;
    private String duracion;
    private boolean premioOscar;


    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getAnoPublicacion() {
        return anoPublicacion;
    }

    public void setAnoPublicacion(int anoPublicacion) {
        this.anoPublicacion = anoPublicacion;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public double getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(double boxOffice) {
        this.boxOffice = boxOffice;
    }





    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public boolean isPremioOscar() {
        return premioOscar;
    }

    public void setPremioOscar(boolean premioOscar) {
        this.premioOscar = premioOscar;
    }

    public genero getGen() {
        return gen;
    }

    public void setGen(genero gen) {
        this.gen = gen;
    }
    public streaming getStream() {
        return stream;
    }

    public void setStream(streaming stream) {
        this.stream = stream;
    }
}
