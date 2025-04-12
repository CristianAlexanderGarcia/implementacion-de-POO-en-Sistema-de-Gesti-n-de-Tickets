package com.example.sistema_tickets.Clases_Modelo;

public class Rol_Administracion extends Clase_Principal_Persona implements Roles{

    public Rol_Administracion(String nombre, String apellido, String correo_Electronico, String contraseña, String nombre_de_Usuario) {
        super(nombre, apellido, correo_Electronico, contraseña, nombre_de_Usuario);

        validarDatos(nombre, apellido, correo_Electronico, contraseña, nombre_de_Usuario);
    }

    private void validarDatos(String nombre, String apellido, String correo, String contra, String usuario) {
        if (nombre == null || nombre.trim().isEmpty()) throw new IllegalArgumentException("El nombre no puede estar vacío.");
        if (apellido == null || apellido.trim().isEmpty()) throw new IllegalArgumentException("El apellido no puede estar vacío.");
        if (correo == null || !correo.contains("@")) throw new IllegalArgumentException("Correo electrónico inválido.");
        if (contra == null || contra.length() < 4) throw new IllegalArgumentException("Contraseña muy corta.");
        if (usuario == null || usuario.trim().isEmpty()) throw new IllegalArgumentException("Usuario no válido.");
    }

    @Override
    public void verTicket() {
        System.out.println("El administrador está viendo el ticket.");
    }

    @Override
    public void agregarNota() {
        System.out.println("El administrador está agregando una nota.");
    }

    public void corregirTicket() {
        System.out.println("El administrador ha corregido un ticket.");
    }

    @Override
    public void realizarAccion() {
        System.out.println("El administrador está revisando y corrigiendo un ticket.");
    }

}//FIN DEL PROGRAMA
