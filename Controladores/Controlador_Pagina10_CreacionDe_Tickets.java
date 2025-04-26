package com.example.sistema_tickets.Controladores;
import com.example.sistema_tickets.Clases_Modelo.Sesion_Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Controlador_Pagina10_CreacionDe_Tickets implements Initializable {

    @FXML
    private TextField AsignarDepartamento;
    @FXML
    private TextField IngresarCantidad_Disponible;
    @FXML
    private TextField IngresarNombre_DeEmpresa;
    @FXML
    private TextField IngresarNotas;
    @FXML
    private TextField IngresarPrioridad;
    @FXML
    private TextField Nombre_Ticket;
    @FXML
    private Button Registrar_Ticket;
    @FXML
    private TextField ingresarDescripcion_Ticket;
    @FXML
    private TextField IngresarCodigo_Ticket;
    @FXML
    private Button RegresarAlMenu;
    @FXML
    private DatePicker FechaVencimientoPicker;
    @FXML
    private DatePicker FechaCreacionPicker;


    private String codigo, nombre, empresa, prioridad, departamento, descripcion, notas;
    private int cantidad;
    private Date fechaVenc;
    private Timestamp fechaCreacion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    @FXML
    void ObtenerCodigo_Ticket(ActionEvent event) {
        codigo = IngresarCodigo_Ticket.getText();
    }

    @FXML
    void obtener_NombreTicket(ActionEvent event) {
        nombre = Nombre_Ticket.getText();
    }

    @FXML
    void ObtenerEmpresa(ActionEvent event) {
        empresa = IngresarNombre_DeEmpresa.getText();
    }

    @FXML
    void ObtenerNivelDePrioridad(ActionEvent event) {
        prioridad = IngresarPrioridad.getText();
    }

    @FXML
    void Obtener_Departamento(ActionEvent event) {
        departamento = AsignarDepartamento.getText();
    }

    @FXML
    void AgregarUna_Descripcion(ActionEvent event) {
        descripcion = ingresarDescripcion_Ticket.getText();
    }

    @FXML
    void AgregarNotas(ActionEvent event) {
        notas = IngresarNotas.getText();
    }

    @FXML
    void Obtener_Cantidad(ActionEvent event) {
        try {
            cantidad = Integer.parseInt(IngresarCantidad_Disponible.getText());
        } catch (NumberFormatException e) {
            System.out.println("Cantidad inválida");
        }
    }

    @FXML
    void ObtenerFechaV(ActionEvent event) {
        LocalDate fechaLocal = FechaVencimientoPicker.getValue();
        if (fechaLocal != null) {
            fechaVenc = Date.valueOf(fechaLocal);
        } else {
            System.out.println("No se ha seleccionado una fecha de vencimiento.");
        }
    }

    @FXML
    void ObtenerFechaC(ActionEvent event) {
        LocalDate fechaLocal = FechaCreacionPicker.getValue();
        if (fechaLocal != null) {
            fechaCreacion = Timestamp.valueOf(fechaLocal.atStartOfDay());
        } else {
            System.out.println("No se ha seleccionado una fecha de creación.");
        }
    }

    @FXML
    void Crear_Ticket(ActionEvent event) {
        String codigo = IngresarCodigo_Ticket.getText();
        String nombre = Nombre_Ticket.getText();
        String empresa = IngresarNombre_DeEmpresa.getText();
        String prioridad = IngresarPrioridad.getText();
        String departamento = AsignarDepartamento.getText();
        String descripcion = ingresarDescripcion_Ticket.getText();
        String notas = IngresarNotas.getText();
        String cantidadTexto = IngresarCantidad_Disponible.getText();

        LocalDate fechaVencLocal = FechaVencimientoPicker.getValue();
        LocalDate fechaCreacionLocal = FechaCreacionPicker.getValue();

        if (codigo.isEmpty() || nombre.isEmpty() || empresa.isEmpty() || prioridad.isEmpty()
                || departamento.isEmpty() || descripcion.isEmpty() || notas.isEmpty()
                || cantidadTexto.isEmpty() || fechaVencLocal == null || fechaCreacionLocal == null) {
            mostrarAlerta("Campos incompletos", "Por favor completa todos los campos.");
            return;
        }

        int cantidad;
        try {
            cantidad = Integer.parseInt(cantidadTexto);
        } catch (NumberFormatException e) {
            mostrarAlerta("Cantidad inválida", "La cantidad debe ser un número entero.");
            return;
        }

        java.sql.Date fechaVenc = java.sql.Date.valueOf(fechaVencLocal);
        java.sql.Timestamp fechaCreacion = java.sql.Timestamp.valueOf(fechaCreacionLocal.atStartOfDay());

        String nombreUsuario = Sesion_Usuario.getUsuarioActual();
        if (nombreUsuario == null || nombreUsuario.isEmpty()) {
            mostrarAlerta("Error", "No hay una sesión de usuario activa.");
            return;
        }

        String url = "jdbc:postgresql://localhost:5432/Gestion_Tickets";
        String user = "postgres";
        String password = "pxrs";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            conn.setAutoCommit(false); // Muy importante para rollback si algo falla

            int idUsuario = -1;
            String sqlUsuario = "SELECT idusuario FROM usuario WHERE usuario = ?";
            try (PreparedStatement stmtUsuario = conn.prepareStatement(sqlUsuario)) {
                stmtUsuario.setString(1, nombreUsuario);
                ResultSet rs = stmtUsuario.executeQuery();
                if (rs.next()) {
                    idUsuario = rs.getInt("idusuario");
                } else {
                    mostrarAlerta("Error", "No se encontró el usuario en la base de datos.");
                    return;
                }
            }

            int idticketGenerado = -1;
            String sql = "INSERT INTO ticket (idusuario, codigo_ticket, nombre_ticket, empresa, prioridad, departamento, descripcion, cantidad_disponible, fecha_vencimiento, fecha_creacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING idticket";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idUsuario);
                stmt.setString(2, codigo);
                stmt.setString(3, nombre);
                stmt.setString(4, empresa);
                stmt.setString(5, prioridad);
                stmt.setString(6, departamento);
                stmt.setString(7, descripcion);
                stmt.setInt(8, cantidad);
                stmt.setDate(9, fechaVenc);
                stmt.setTimestamp(10, fechaCreacion);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    idticketGenerado = rs.getInt("idticket");
                } else {
                    throw new SQLException("No se pudo recuperar el ID del ticket creado.");
                }
            }

            String sqlNota = "INSERT INTO nota_ticket (idticket, idusuario, contenido) VALUES (?, ?, ?)";
            try (PreparedStatement stmtNota = conn.prepareStatement(sqlNota)) {
                stmtNota.setInt(1, idticketGenerado);
                stmtNota.setInt(2, idUsuario); // Asumimos que el usuario es quien escribe la nota
                stmtNota.setString(3, notas);
                stmtNota.executeUpdate();
            }

            conn.commit();
            mostrarAlerta("Ticket creado", "¡Ticket y nota creados exitosamente!", Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo crear el ticket. Verifica los datos.");
        }
    }


    // Métodos auxiliares para mostrar alertas
    private void mostrarAlerta(String titulo, String mensaje) {
        mostrarAlerta(titulo, mensaje, Alert.AlertType.ERROR);
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }


    @FXML
    void Regreso(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_tickets/Pagina7_PantalladeGestion.fxml"));
            Pane previousWindow = loader.load();
            Scene previousScene = new Scene(previousWindow);
            Stage stage = (Stage) RegresarAlMenu.getScene().getWindow();
            stage.setScene(previousScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String contenido) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

}//FIN DEL PROGRAMA
