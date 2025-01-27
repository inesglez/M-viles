package com.example.cineparte2;


public class Usuario {
    private String nombre;
    private String clave;
    private String rol;  // "normal" o "administrador"

    public Usuario(String nombre, String clave, String rol) {
        this.nombre = nombre;
        this.clave = clave;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public String getClave() {
        return clave;
    }

    public String getRol() {
        return rol;
    }
}
