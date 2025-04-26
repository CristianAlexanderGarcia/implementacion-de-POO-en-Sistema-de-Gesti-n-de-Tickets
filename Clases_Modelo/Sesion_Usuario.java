package com.example.sistema_tickets.Clases_Modelo;

public class Sesion_Usuario {
    private static String usuarioActual;
    private static int idUsuarioActual;

    public static void setUsuarioActual(String usuario) {
        usuarioActual = usuario;
    }

    public static String getUsuarioActual() {
        return usuarioActual;
    }

    public static void setIdUsuarioActual(int id) {
        idUsuarioActual = id;
    }

    public static int getIdUsuarioActual() {
        return idUsuarioActual;
    }

    public static void cerrarSesion() {
        usuarioActual = null;
        idUsuarioActual = 0;
    }
}

