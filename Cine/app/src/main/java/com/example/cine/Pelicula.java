package com.example.cine;


    public class Pelicula {
        private String titulo;
        private String genero;
        private String sinopsis;
        private int duracion;  // Duración en minutos
        private float puntuacion;  // Puntuación entre 0 y 5
        private int imagenResId;  // ID del recurso de la imagen

        // Constructor
        public Pelicula(String titulo, String genero, String sinopsis, int duracion, float puntuacion, int imagenResId) {
            this.titulo = titulo;
            this.genero = genero;
            this.sinopsis = sinopsis;
            this.duracion = duracion;
            this.puntuacion = puntuacion;
            this.imagenResId = imagenResId;
        }

        // Getters y Setters
        public String getTitulo() {
            return titulo;
        }

        public String getGenero() {
            return genero;
        }

        public String getSinopsis() {
            return sinopsis;
        }

        public int getDuracion() {
            return duracion;
        }

        public float getPuntuacion() {
            return puntuacion;
        }

        public int getImagenResId() {
            return imagenResId;
        }

        public void setImagenResId(int imagenResId) {
            this.imagenResId = imagenResId;
        }
    }
