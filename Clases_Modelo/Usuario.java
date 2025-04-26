package com.example.sistema_tickets.Clases_Modelo;

public class Usuario {

    private String nombre;
    private String apellido;
    private String correo_Electronico;
    private String nombre_de_Usuario;
    private String contrasena;

    // Constructor
    public Usuario(String nombre, String apellido, String correo_Electronico, String nombre_de_Usuario, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo_Electronico = correo_Electronico;
        this.nombre_de_Usuario = nombre_de_Usuario;
        this.contrasena = contrasena;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo_Electronico() {
        return correo_Electronico;
    }

    public String getNombre_de_Usuario() {
        return nombre_de_Usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCorreo_Electronico(String correo_Electronico) {
        this.correo_Electronico = correo_Electronico;
    }

    public void setNombre_de_Usuario(String nombre_de_Usuario) {
        this.nombre_de_Usuario = nombre_de_Usuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public String getCorreo() {
        return correo_Electronico;
    }

    public String getUsuario() {
        return nombre_de_Usuario;
    }


}//FIN DEL PROGRAMA
