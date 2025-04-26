package com.example.sistema_tickets.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controlador_Pagina17_2_Crud_Usuario implements Initializable {

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
    void AgregarUsuario(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Actualizar Usuario");
        dialog.setHeaderText(null);
        dialog.setContentText("Ingresa el nombre de usuario a actualizar:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(nombreUsuario -> {
            nombreUsuario = nombreUsuario.trim();
            try (Connection conn = conectar()) {
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Usuario WHERE usuario ILIKE ?");
                stmt.setString(1, nombreUsuario);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    TextInputDialog nuevoNombre = new TextInputDialog();
                    nuevoNombre.setTitle("Actualizar Nombre");
                    nuevoNombre.setContentText("Nuevo nombre:");
                    Optional<String> nombre = nuevoNombre.showAndWait();

                    TextInputDialog nuevaContra = new TextInputDialog();
                    nuevaContra.setTitle("Actualizar Contraseña");
                    nuevaContra.setContentText("Nueva contraseña:");
                    Optional<String> contra = nuevaContra.showAndWait();

                    if (nombre.isPresent() && contra.isPresent()) {
                        PreparedStatement updateStmt = conn.prepareStatement("UPDATE Usuario SET nombre = ?, contrasena = ? WHERE usuario ILIKE ?");
                        updateStmt.setString(1, nombre.get());
                        updateStmt.setString(2, contra.get());
                        updateStmt.setString(3, nombreUsuario);
                        updateStmt.executeUpdate();
                        mostrarAlerta("Éxito", "Usuario actualizado correctamente.");
                    }
                } else {
                    mostrarAlerta("Error", "Usuario no encontrado.");
                }
            } catch (Exception e) {
                mostrarAlerta("Error", "Error al actualizar usuario: " + e.getMessage());
            }
        });
    }

    @FXML
    void CrearUsuario(ActionEvent event) {
        TextInputDialog nombreDialog = new TextInputDialog();
        nombreDialog.setTitle("Crear Usuario");
        nombreDialog.setHeaderText("Ingrese el nombre del nuevo usuario");
        nombreDialog.setContentText("Nombre:");
        Optional<String> nombre = nombreDialog.showAndWait();
        if (nombre.isEmpty()) return;

        TextInputDialog apellidoDialog = new TextInputDialog();
        apellidoDialog.setTitle("Crear Usuario");
        apellidoDialog.setHeaderText("Ingrese el apellido del nuevo usuario");
        apellidoDialog.setContentText("Apellido:");
        Optional<String> apellido = apellidoDialog.showAndWait();
        if (apellido.isEmpty()) return;

        TextInputDialog correoDialog = new TextInputDialog();
        correoDialog.setTitle("Crear Usuario");
        correoDialog.setHeaderText("Ingrese el correo electrónico");
        correoDialog.setContentText("Correo:");
        Optional<String> correo = correoDialog.showAndWait();
        if (correo.isEmpty()) return;

        TextInputDialog usuarioDialog = new TextInputDialog();
        usuarioDialog.setTitle("Crear Usuario");
        usuarioDialog.setHeaderText("Ingrese el nombre de usuario");
        usuarioDialog.setContentText("Usuario:");
        Optional<String> usuario = usuarioDialog.showAndWait();
        if (usuario.isEmpty()) return;

        TextInputDialog contrasenaDialog = new TextInputDialog();
        contrasenaDialog.setTitle("Crear Usuario");
        contrasenaDialog.setHeaderText("Ingrese la contraseña");
        contrasenaDialog.setContentText("Contraseña:");
        Optional<String> contrasena = contrasenaDialog.showAndWait();
        if (contrasena.isEmpty()) return;

        try (Connection conn = conectar()) {
            String sql = "INSERT INTO Usuario (nombre, apellido, correo, usuario, contrasena) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nombre.get());
                stmt.setString(2, apellido.get());
                stmt.setString(3, correo.get());
                stmt.setString(4, usuario.get());
                stmt.setString(5, contrasena.get());
                stmt.executeUpdate();
                mostrarAlerta("Éxito", "Usuario creado correctamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo crear el usuario: " + e.getMessage());
        }
    }

    @FXML
    void EliminarUsuario(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Eliminar Usuario");
        dialog.setHeaderText(null);
        dialog.setContentText("Ingresa el nombre de usuario a eliminar:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(nombreUsuario -> {
            nombreUsuario = nombreUsuario.trim();
            try (Connection conn = conectar()) {
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Usuario WHERE usuario ILIKE ?");
                stmt.setString(1, nombreUsuario);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    PreparedStatement deleteStmt = conn.prepareStatement("DELETE FROM Usuario WHERE usuario ILIKE ?");
                    deleteStmt.setString(1, nombreUsuario);
                    deleteStmt.executeUpdate();
                    mostrarAlerta("Éxito", "Usuario eliminado correctamente.");
                } else {
                    mostrarAlerta("Error", "Usuario no encontrado.");
                }
            } catch (Exception e) {
                mostrarAlerta("Error", "Error al eliminar usuario: " + e.getMessage());
            }
        });
    }

    @FXML
    void Regresa(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_tickets/Pagina17_MenuGestionUsuarios.fxml"));
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
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}//FIN DEL PROGRAMA
