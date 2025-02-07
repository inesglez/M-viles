package com.example.cine;

import java.io.Serializable;

public class Pelicula implements Serializable {
    private String titulo;
    private String sinopsis;
    private String genero;
    private int duracion;
    private float puntuacion;
    private int imagenResId; // ID de la imagen (recurso de drawable)
    private byte[] imagenCapturada; // Imagen tomada con la cámara en formato byte[]

    // Constructor con imagen capturada
    public Pelicula(String titulo, String sinopsis, String genero, int duracion, float puntuacion, int imagenResId, byte[] imagenCapturada) {
        this.titulo = titulo;
        this.sinopsis = sinopsis;
        this.genero = genero;
        this.duracion = duracion;
        this.puntuacion = puntuacion;
        this.imagenResId = imagenResId;
        this.imagenCapturada = imagenCapturada;
    }

    // Getters y Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public float getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(float puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getImagenResId() {
        return imagenResId;
    }

    public void setImagenResId(int imagenResId) {
        this.imagenResId = imagenResId;
    }

    public byte[] getImagenCapturada() {
        return imagenCapturada;
    }

    public void setImagenCapturada(byte[] imagenCapturada) {
        this.imagenCapturada = imagenCapturada;
    }
    public byte[] getImagenBytes() { return imagenCapturada; }
    public void setImagenBytes(byte[] imagenBytes) { this.imagenCapturada= imagenCapturada; }

}

