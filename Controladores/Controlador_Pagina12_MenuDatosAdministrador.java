package com.example.sistema_tickets.Controladores;

import com.example.sistema_tickets.Clases_Modelo.Sesion_Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class Controlador_Pagina12_MenuDatosAdministrador implements Initializable {

    @FXML
    private Button Acceder;

    @FXML
    private TextField Apellido_Admin;

    @FXML
    private TextField Contra_Admin;

    @FXML
    private TextField Correo_Admin;

    @FXML
    private Button Iniciar_Sesion;

    @FXML
    private TextField NombreAdmin;

    @FXML
    private Button Registrar_Persona;

    @FXML
    private TextField Usuario_Admin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    @FXML
    void Agregar_Administrador(ActionEvent event) {
        String nombre = NombreAdmin.getText();
        String usuario = Usuario_Admin.getText();
        String apellido = Apellido_Admin.getText();
        String correo = Correo_Admin.getText();
        String contrasena = Contra_Admin.getText();

        if (nombre.isEmpty() || usuario.isEmpty() || apellido.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
            System.out.println("Todos los campos deben estar llenos.");
            return;
        }

        String url = "jdbc:postgresql://localhost:5432/Gestion_Tickets";
        String user = "postgres";
        String password = "pxrs";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO administrador (nombre, apellido, usuario, correo, contrasena) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, usuario);
            stmt.setString(4, correo);
            stmt.setString(5, contrasena);
            stmt.executeUpdate();

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Registro exitoso");
            alert.setHeaderText(null);
            alert.setContentText("¡Administrador creado exitosamente!");
            alert.showAndWait();

            Sesion_Usuario.setUsuarioActual(usuario);

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo registrar al administrador");
            alert.setContentText("Verifica que los datos estén correctos o si ya existe el administrador.");
            alert.showAndWait();
        }
    }

    @FXML
    void IngresarContrase(ActionEvent event) {
        String usuario = Usuario_Admin.getText();
        String contrasena = Contra_Admin.getText();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            mostrarAlerta("Advertencia", "Debes ingresar el usuario y la contraseña.");
            return;
        }

        ejecutarUpdate("UPDATE administrador SET contrasena = ? WHERE usuario = ?", contrasena, usuario, "Contraseña actualizada correctamente.");
    }

    @FXML
    void IngresarNombreAdmin(ActionEvent event) {
        String usuario = Usuario_Admin.getText();
        String nombre = NombreAdmin.getText();

        if (usuario.isEmpty() || nombre.isEmpty()) {
            mostrarAlerta("Advertencia", "Debes ingresar el usuario y el nombre.");
            return;
        }

        ejecutarUpdate("UPDATE administrador SET nombre = ? WHERE usuario = ?", nombre, usuario, "Nombre actualizado correctamente.");
    }

    @FXML
    void Ingreso_delNombreAd(ActionEvent event) {
        // Este campo es el nombre de usuario
        String usuario = Usuario_Admin.getText();

        if (usuario.isEmpty()) {
            mostrarAlerta("Advertencia", "Debes ingresar el nombre de usuario.");
            return;
        }

        // Este método no actualiza nada, pero puedes usarlo si necesitas mostrar algo o confirmar
        mostrarAlerta("Información", "Nombre de usuario ingresado: " + usuario);
    }

    @FXML
    void Registrar_Apellido(ActionEvent event) {
        String usuario = Usuario_Admin.getText();
        String apellido = Apellido_Admin.getText();

        if (usuario.isEmpty() || apellido.isEmpty()) {
            mostrarAlerta("Advertencia", "Debes ingresar el usuario y el apellido.");
            return;
        }

        ejecutarUpdate("UPDATE administrador SET apellido = ? WHERE usuario = ?", apellido, usuario, "Apellido actualizado correctamente.");
    }

    @FXML
    void Registrar_CorreoAdmin(ActionEvent event) {
        String usuario = Usuario_Admin.getText();
        String correo = Correo_Admin.getText();

        if (usuario.isEmpty() || correo.isEmpty()) {
            mostrarAlerta("Advertencia", "Debes ingresar el usuario y el correo.");
            return;
        }

        ejecutarUpdate("UPDATE administrador SET correo = ? WHERE usuario = ?", correo, usuario, "Correo actualizado correctamente.");
    }
    private void ejecutarUpdate(String sql, String valor, String usuario, String mensajeExito) {
        String url = "jdbc:postgresql://localhost:5432/Gestion_Tickets";
        String dbUser = "postgres";
        String dbPassword = "pxrs";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword)) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, valor);
            stmt.setString(2, usuario);
            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                mostrarAlerta("Éxito", mensajeExito);
            } else {
                mostrarAlerta("Error", "No se encontró ningún administrador con ese usuario.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "Hubo un problema al actualizar en la base de datos.");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    void Verificar_Existencia(ActionEvent event) {
        mostrarDialogoInicioSesion();
    }

    private void mostrarDialogoInicioSesion() {
        TextField campoCorreo = new TextField();
        campoCorreo.setPromptText("Correo");

        PasswordField campoContrasena = new PasswordField();
        campoContrasena.setPromptText("Contraseña");

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(new Label("Correo:"), campoCorreo, new Label("Contraseña:"), campoContrasena);
        vbox.setPadding(new Insets(10));

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Iniciar sesión");
        dialog.getDialogPane().setContent(vbox);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                String correo = campoCorreo.getText();
                String contrasena = campoContrasena.getText();
                verificarCredenciales(correo, contrasena);
            }
        });
    }

    private void verificarCredenciales(String correo, String contrasena) {
        String url = "jdbc:postgresql://localhost:5432/Gestion_Tickets";
        String user = "postgres";
        String password = "pxrs";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT usuario FROM administrador WHERE correo = ? AND contrasena = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, correo);
            stmt.setString(2, contrasena);

            var rs = stmt.executeQuery();

            if (rs.next()) {
                String usuario = rs.getString("usuario");
                Sesion_Usuario.setUsuarioActual(usuario);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_tickets/Pagina13_MenuDeOpciones.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) Iniciar_Sesion.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Acceso denegado");
                alert.setHeaderText(null);
                alert.setContentText("Correo o contraseña incorrectos.");
                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo verificar al administrador");
            alert.setContentText("Verifica la conexión con la base de datos.");
            alert.showAndWait();
        }
    }

    @FXML
    void Accesoalmenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_tickets/Pagina13_MenuDeOpciones.fxml"));
            Pane secondWindow = loader.load();
            Scene secondScene = new Scene(secondWindow);
            Stage stage = (Stage) Acceder.getScene().getWindow();
            stage.setScene(secondScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//FIN DEL PROGRAMA
