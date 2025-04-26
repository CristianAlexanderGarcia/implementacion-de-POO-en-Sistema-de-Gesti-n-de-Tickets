package com.example.sistema_tickets.Clases_Modelo;

public class Gestion_Tickets {

    private String Departamento;
    private String Empresa;
    private String Nivel_De_prioridad;
    private String codigo_ticket;

    public Gestion_Tickets(String departamento, String empresa, String nivel_De_prioridad, String codigo_ticket) {
        this.Departamento = departamento;
        this.Empresa = empresa;
        this.Nivel_De_prioridad = nivel_De_prioridad;
        this.codigo_ticket = codigo_ticket;
    }

    public String getDepartamento() {
        return Departamento;
    }

    public void setDepartamento(String departamento) {
        Departamento = departamento;
    }

    public String getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(String empresa) {
        Empresa = empresa;
    }

    public String getNivel_De_prioridad() {
        return Nivel_De_prioridad;
    }

    public void setNivel_De_prioridad(String nivel_De_prioridad) {
        Nivel_De_prioridad = nivel_De_prioridad;
    }

    public String getCodigo_ticket() {
        return codigo_ticket;
    }

    public void setCodigo_ticket(String codigo_ticket) {
        this.codigo_ticket = codigo_ticket;
    }

}//FIN DEL PROGRAMA
