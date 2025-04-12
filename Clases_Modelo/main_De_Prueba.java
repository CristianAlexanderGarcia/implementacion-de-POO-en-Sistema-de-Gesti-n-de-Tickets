package com.example.sistema_tickets.Clases_Modelo;

public class main_De_Prueba {
    public static void main(String[] args) {

        try {

            Roles usuario = new Rol_Usuario("Pedro", "Pérez", "pedro@email.com", "1234", "pedro123");

            Roles admin = new Rol_Administracion("Lucía", "Gómez", "lucia@email.com", "abcd1234", "luciaAdmin");

            Roles tecnico = new Rol_Tecnico("Carlos", "Martínez", "carlos@email.com", "tec9876", "carlosTech");

            realizarAccionPorRol(usuario);
            realizarAccionPorRol(admin);
            realizarAccionPorRol(tecnico);

        } catch (IllegalArgumentException e) {
            System.out.println(" Error al crear un rol: " + e.getMessage());
        }

    }

    public static void realizarAccionPorRol(Roles rol) {
        System.out.println("🔹 Rol: " + rol.getClass().getSimpleName());
        rol.realizarAccion();
        System.out.println(" Acción realizada correctamente\n");
    }
}//FIN DEL PROGRAMA
