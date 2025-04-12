package com.example.sistema_tickets.Conexion_BaseDe_Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Clase_Conexion_Pos {
    public static void main(String[] args) {

        String user = "postgres";
        String pass = "pxrs";

        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Gestion_Tickets", user,pass);
            System.out.println("conectado con exito");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

}

}//FIN DEL PROGRAMA
