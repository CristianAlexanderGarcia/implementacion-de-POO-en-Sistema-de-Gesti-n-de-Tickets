//CLASE PARA CAMBIAR DE IDIOMA ESPAÑOL A INGLES Y VICEVERSA

    package com.example.sistema_tickets.Clases_Modelo;

    import java.util.Locale;
    import java.util.ResourceBundle;

    public class IdiomaS_Global {

        private static Locale idiomaActual = new Locale("es", "ES");

        public static void cambiarIdioma(Locale nuevoIdioma) {
            idiomaActual = nuevoIdioma;
        }

        public static ResourceBundle getBundle() {
            return ResourceBundle.getBundle("com.example.sistema_tickets.Idiomas.mensajes", idiomaActual);
        }

        public static Locale getIdiomaActual() {
            return idiomaActual;
        }

    }//FIN DEL PROGRAMA
