package com.example.sistema_tickets.Clases_Modelo;

public class Datos_Tickets extends Gestion_Tickets{

    private String Nombre;
    private int Cantidad_Disponible;
    private String Fecha_de_Creacion;
    private String Fecha_de_Vencimiento;
    private String Notas;
    private String Descripcion_sobre_el_Ticket;
    private String Estado_Ticket;

    public Datos_Tickets(String departamento, String empresa, String nivel_De_prioridad, String nombre, int cantidad_Disponible, String fecha_de_Creacion, String fecha_de_Vencimiento, String notas, String descripcion_sobre_el_Ticket, String estado_Ticket) {
        super(departamento, empresa, nivel_De_prioridad);
        this.Nombre = nombre;
        this.Cantidad_Disponible = cantidad_Disponible;
        this.Fecha_de_Creacion = fecha_de_Creacion;
        this.Fecha_de_Vencimiento = fecha_de_Vencimiento;
        this.Notas = notas;
        this.Descripcion_sobre_el_Ticket = descripcion_sobre_el_Ticket;
        this.Estado_Ticket = "pendiente";
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getCantidad_Disponible() {
        return Cantidad_Disponible;
    }

    public void setCantidad_Disponible(int cantidad_Disponible) {
        Cantidad_Disponible = cantidad_Disponible;
    }

    public String getFecha_de_Creacion() {
        return Fecha_de_Creacion;
    }

    public void setFecha_de_Creacion(String fecha_de_Creacion) {
        Fecha_de_Creacion = fecha_de_Creacion;
    }

    public String getFecha_de_Vencimiento() {
        return Fecha_de_Vencimiento;
    }

    public void setFecha_de_Vencimiento(String fecha_de_Vencimiento) {
        Fecha_de_Vencimiento = fecha_de_Vencimiento;
    }

    public String getNotas() {
        return Notas;
    }

    public void setNotas(String notas) {
        Notas = notas;
    }

    public String getDescripcion_sobre_el_Ticket() {
        return Descripcion_sobre_el_Ticket;
    }

    public void setDescripcion_sobre_el_Ticket(String descripcion_sobre_el_Ticket) {
        Descripcion_sobre_el_Ticket = descripcion_sobre_el_Ticket;
    }

    public String getEstado_Ticket() {
        return Estado_Ticket;
    }

    public void setEstado_Ticket(String estado_Ticket) {
        Estado_Ticket = estado_Ticket;
    }


}//FIN DEL PROGRAMA
