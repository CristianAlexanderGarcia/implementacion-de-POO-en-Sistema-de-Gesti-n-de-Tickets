package com.example.sistema_tickets.Clases_Modelo;

public class Rol_Usuario extends Clase_Principal_Persona implements Roles{

    public Rol_Usuario(String nombre, String apellido, String correo_Electronico, String contraseña, String nombre_de_Usuario){
        super(nombre, apellido, correo_Electronico, contraseña, nombre_de_Usuario);

        validarDatos(nombre, apellido, correo_Electronico, contraseña, nombre_de_Usuario);
    }

    private void validarDatos(String nombre, String apellido, String correo, String contra, String usuario) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío.");
        }
        if (correo == null || !correo.contains("@")) {
            throw new IllegalArgumentException("Correo electrónico inválido.");
        }
        if (contra == null || contra.length() < 4) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 4 caracteres.");
        }
        if (usuario == null || usuario.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío.");
        }
    }


    @Override
    public void verTicket() {
        System.out.println("El usuario está viendo el ticket.");
    }

    @Override
    public void agregarNota() {
        System.out.println("El usuario está agregando una nota al ticket.");
    }

    public void crearTicket() {
        System.out.println("El usuario ha creado un ticket.");
    }

    @Override
    public void realizarAccion() {
        System.out.println("El usuario está creando un ticket.");
    }

}//FIN DEL PROGRAMA
