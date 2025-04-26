package com.example.sistema_tickets.Controladores;

import com.example.sistema_tickets.Clases_Modelo.Datos_Tickets;
import com.example.sistema_tickets.Clases_Modelo.Sesion_Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controlador_Pagina19_2_Crud_Ticket implements Initializable {

    @FXML
    private Button BotonAgregar;

    @FXML
    private Button BotonCrear;

    @FXML
    private Button BotonEliminar;

    @FXML
    private Button RegresoAlGestor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    String url = "jdbc:postgresql://localhost:5432/Gestion_Tickets";
    String user = "postgres";
    String password = "pxrs";

    private Connection conectar() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            mostrarAlerta("Error de conexión", "No se pudo conectar a la base de datos.");
            return null;
        }
    }
    @FXML
    void CrearTicket(ActionEvent event) {

        TextInputDialog nombreDialog = new TextInputDialog();
        nombreDialog.setTitle("Crear Ticket");
        nombreDialog.setHeaderText("Creación de Ticket");
        nombreDialog.setContentText("Nombre del Ticket:");
        Optional<String> nombre = nombreDialog.showAndWait();
        if (nombre.isEmpty() || nombre.get().trim().isEmpty()) {
            mostrarAlerta("Campo requerido", "Debe ingresar un nombre para el ticket.");
            return;
        }


        Optional<String> empresa = pedirDato("Empresa:");
        if (empresa.isEmpty() || empresa.get().trim().isEmpty()) return;

        Optional<String> depto = pedirDato("Departamento:");
        if (depto.isEmpty() || depto.get().trim().isEmpty()) return;

        Optional<String> prioridad = pedirDato("Prioridad:");
        if (prioridad.isEmpty() || prioridad.get().trim().isEmpty()) return;

        Optional<String> descripcion = pedirDato("Descripción del ticket:");
        if (descripcion.isEmpty() || descripcion.get().trim().isEmpty()) return;

        try (Connection conn = conectar()) {
            String sql = "INSERT INTO Ticket (idusuario, nombre_ticket, empresa, departamento, prioridad, descripcion, fecha_creacion, fecha_vencimiento) " +
                    "VALUES (?, ?, ?, ?, ?, ?, CURRENT_DATE, CURRENT_DATE + INTERVAL '7 days')";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombre.get().trim());
            stmt.setString(2, empresa.get().trim());
            stmt.setString(3, depto.get().trim());
            stmt.setString(4, prioridad.get().trim());
            stmt.setString(5, descripcion.get().trim());
            stmt.setInt(6, Sesion_Usuario.getIdUsuarioActual()); // <- 👈 Aquí añadimos el ID del usuario actual

            stmt.executeUpdate();
            mostrarAlerta("Éxito", "Ticket creado correctamente.");
        } catch (SQLException e) {
            mostrarAlerta("Error", "No se pudo crear el ticket: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Optional<String> pedirDato(String mensaje) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Dato requerido");
        dialog.setHeaderText(null);
        dialog.setContentText(mensaje);
        return dialog.showAndWait();
    }

    @FXML
    void AgregarTicket(ActionEvent event) {
        TextInputDialog idDialog = new TextInputDialog();
        idDialog.setTitle("Actualizar Ticket");
        idDialog.setHeaderText(null);
        idDialog.setContentText("Ingresa el código del ticket a actualizar:");

        Optional<String> result = idDialog.showAndWait();
        result.ifPresent(codigo -> {
            if (!codigo.trim().isEmpty()) {
                try (Connection conn = conectar()) {
                    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Ticket WHERE id_ticket = ?");
                    stmt.setString(1, codigo.trim());
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        // Diálogos para los nuevos datos:
                        TextInputDialog empresaDialog = new TextInputDialog(rs.getString("empresa"));
                        empresaDialog.setTitle("Actualizar Ticket");
                        empresaDialog.setHeaderText("Empresa:");
                        Optional<String> empresa = empresaDialog.showAndWait();

                        TextInputDialog deptoDialog = new TextInputDialog(rs.getString("departamento"));
                        deptoDialog.setTitle("Actualizar Ticket");
                        deptoDialog.setHeaderText("Departamento:");
                        Optional<String> departamento = deptoDialog.showAndWait();

                        TextInputDialog prioridadDialog = new TextInputDialog(rs.getString("prioridad"));
                        prioridadDialog.setTitle("Actualizar Ticket");
                        prioridadDialog.setHeaderText("Prioridad:");
                        Optional<String> prioridad = prioridadDialog.showAndWait();

                        TextInputDialog descripcionDialog = new TextInputDialog(rs.getString("descripcion"));
                        descripcionDialog.setTitle("Actualizar Ticket");
                        descripcionDialog.setHeaderText("Descripción:");
                        Optional<String> descripcion = descripcionDialog.showAndWait();

                        if (empresa.isPresent() && departamento.isPresent() && prioridad.isPresent() && descripcion.isPresent()) {
                            PreparedStatement updateStmt = conn.prepareStatement(
                                    "UPDATE Ticket SET nombre_ticket = ?, descripcion = ? WHERE idticket = ?"
                            );
                            updateStmt.setString(1, empresa.get());
                            updateStmt.setString(2, departamento.get());
                            updateStmt.setString(3, prioridad.get());
                            updateStmt.setString(4, descripcion.get());
                            updateStmt.setString(5, codigo.trim());
                            updateStmt.executeUpdate();
                            mostrarAlerta("Éxito", "Ticket actualizado correctamente.");
                        }

                    } else {
                        mostrarAlerta("Error", "Ticket no encontrado.");
                    }

                } catch (Exception e) {
                    mostrarAlerta("Error", "Error al actualizar ticket: " + e.getMessage());
                }
            } else {
                mostrarAlerta("Entrada inválida", "El código del ticket no puede estar vacío.");
            }
        });
    }

    @FXML
    void EliminarTicket(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Eliminar Ticket");
        dialog.setHeaderText(null);
        dialog.setContentText("Ingresa el código del ticket a eliminar:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(codigo -> {
            if (!codigo.trim().isEmpty()) {
                try (Connection conn = conectar()) {
                    PreparedStatement checkStmt = conn.prepareStatement("SELECT * FROM Ticket WHERE id_ticket = ?");
                    checkStmt.setString(1, codigo.trim());
                    ResultSet rs = checkStmt.executeQuery();

                    if (rs.next()) {
                        PreparedStatement deleteStmt = conn.prepareStatement("DELETE FROM Ticket WHERE idticket = ?");
                        deleteStmt.setString(1, codigo.trim());
                        deleteStmt.executeUpdate();
                        mostrarAlerta("Éxito", "Ticket eliminado correctamente.");
                    } else {
                        mostrarAlerta("Error", "No se encontró un ticket con ese código.");
                    }

                } catch (Exception e) {
                    mostrarAlerta("Error", "Error al eliminar el ticket: " + e.getMessage());
                }
            } else {
                mostrarAlerta("Entrada inválida", "El código del ticket no puede estar vacío.");
            }
        });
    }



    @FXML
    void Regresa(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_tickets/Pagina19_MenuGestorDe_Tickets.fxml"));
            Pane previousWindow = loader.load();
            Scene previousScene = new Scene(previousWindow);
            Stage stage = (Stage) RegresoAlGestor.getScene().getWindow();
            stage.setScene(previousScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}// FIN DEL PROGRAMA


