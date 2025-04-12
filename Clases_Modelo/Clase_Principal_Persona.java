package com.example.sistema_tickets.Clases_Modelo;

public class Clase_Principal_Persona {

    private String Nombre;
    private String Apellido;
    private String Correo_Electronico;
    private String Contraseña;
    private String Nombre_de_Usuario;

    public Clase_Principal_Persona(String nombre, String apellido, String correo_Electronico, String contraseña, String nombre_de_Usuario) {
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.Correo_Electronico = correo_Electronico;
        this.Contraseña = contraseña;
        this.Nombre_de_Usuario = nombre_de_Usuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getCorreo_Electronico() {
        return Correo_Electronico;
    }

    public void setCorreo_Electronico(String correo_Electronico) {
        Correo_Electronico = correo_Electronico;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    public String getNombre_de_Usuario() {
        return Nombre_de_Usuario;
    }

    public void setNombre_de_Usuario(String nombre_de_Usuario) {
        Nombre_de_Usuario = nombre_de_Usuario;
    }

}//FIN DEL PROGRAMA
