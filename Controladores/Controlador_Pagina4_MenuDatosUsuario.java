package com.example.sistema_tickets.Controladores;

import com.example.sistema_tickets.Clases_Modelo.Sesion_Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.geometry.Insets;


public class Controlador_Pagina4_MenuDatosUsuario implements Initializable {

    @FXML
    private TextField Apellido_Usuario;

    @FXML
    private Button Registrar_Persona;

    @FXML
    private Button BotonPrincipal;

    @FXML
    private TextField Contra_Usu;

    @FXML
    private TextField Correo_Usuario;

    @FXML
    private Button Iniciar_sesion;

    @FXML
    private TextField Nombre2_del_usuario;

    @FXML
    private TextField Nombre_Principal_del_usuario;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    void LlevaAlMenuPrincipal(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_tickets/Pagina5_Acceso.fxml"));
            Pane secondWindow = loader.load();
            Scene secondScene = new Scene(secondWindow);
            Stage stage = (Stage) BotonPrincipal.getScene().getWindow();
            stage.setScene(secondScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    void Registrar_Nombre(ActionEvent event) {
        String nombre_de_usuario = Nombre2_del_usuario.getText();

        if (nombre_de_usuario.isEmpty()) {
            System.out.println(" El campo de nombre_de_usuario está vacío.");
            return;
        }

        String url = "jdbc:postgresql://localhost:5432/Gestion_Tickets";
        String user = "postgres";
        String password = "pxrs";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO usuarios (usuario) VALUES (?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombre_de_usuario);

            stmt.executeUpdate();
            System.out.println(" nombre_de_usuario registrado con éxito: " + nombre_de_usuario);

            Nombre2_del_usuario.clear();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Error al registrar el nombre_de_usuario.");
        }
    }

    @FXML
    void Registro_Apellido(ActionEvent event) {
        String apellido = Apellido_Usuario.getText();

        if (apellido.isEmpty()) {
            System.out.println(" El campo de apellido está vacío.");
            return;
        }

        String url = "jdbc:postgresql://localhost:5432/Gestion_Tickets";
        String user = "postgres";
        String password = "pxrs";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO usuarios (apellido) VALUES (?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, apellido);

            stmt.executeUpdate();
            System.out.println(" apellido registrado con éxito: " + apellido);

            Apellido_Usuario.clear();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Error al registrar el apellido.");
        }
    }

    @FXML
    void Registro_Nombre_Principal(ActionEvent event) {
        String nombre = Nombre_Principal_del_usuario.getText();

        if (nombre.isEmpty()) {
            System.out.println(" El campo de nombre está vacío.");
            return;
        }

        String url = "jdbc:postgresql://localhost:5432/Gestion_Tickets";
        String user = "postgres";
        String password = "pxrs";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO usuarios (nombre) VALUES (?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombre);

            stmt.executeUpdate();
            System.out.println(" Nombre registrado con éxito: " + nombre);

            Nombre_Principal_del_usuario.clear();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Error al registrar el nombre.");
        }
    }

    @FXML
    void Registro_de_Correo(ActionEvent event) {
        String correo_electronico = Correo_Usuario.getText();

        if (correo_electronico.isEmpty()) {
            System.out.println(" El campo de correo_electronico está vacío.");
            return;
        }

        String url = "jdbc:postgresql://localhost:5432/Gestion_Tickets";
        String user = "postgres";
        String password = "pxrs";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO usuarios (correo) VALUES (?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, correo_electronico);

            stmt.executeUpdate();
            System.out.println(" correo_electronico registrado con éxito: " + correo_electronico);

            Correo_Usuario.clear();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Error al registrar el correo_electronico.");
        }

    }

    @FXML
    void Registrar_Contrase(ActionEvent event) {
        String contrasena = Contra_Usu.getText();

        if (contrasena.isEmpty()) {
            System.out.println(" El campo de contraseña está vacío.");
            return;
        }

        String url = "jdbc:postgresql://localhost:5432/Gestion_Tickets";
        String user = "postgres";
        String password = "pxrs";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO usuarios (contrasena) VALUES (?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, contrasena);

            stmt.executeUpdate();
            System.out.println(" contraseña registrado con éxito: " + contrasena);

            Contra_Usu.clear();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Error al registrar el contraseña.");
        }

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
            String sql = "SELECT idusuario, usuario FROM usuario WHERE correo = ? AND contrasena = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, correo);
            stmt.setString(2, contrasena);

            var rs = stmt.executeQuery();

            if (rs.next()) {
                int idUsuario = rs.getInt("idusuario");
                String usuario = rs.getString("usuario");

                Sesion_Usuario.setUsuarioActual(usuario);
                Sesion_Usuario.setIdUsuarioActual(idUsuario); // 👈 NUEVO

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_tickets/Pagina5_Acceso.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) Iniciar_sesion.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            }
            else {
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
            alert.setHeaderText("No se pudo verificar el usuario");
            alert.setContentText("Verifica la conexión con la base de datos.");
            alert.showAndWait();
        }
    }

    @FXML
    void Verifcar_usuario_existente(ActionEvent event) {
        mostrarDialogoInicioSesion();
    }




    @FXML
    void Agregar_Usuario(ActionEvent event) {
        String nombre = Nombre_Principal_del_usuario.getText();
        String usuario = Nombre2_del_usuario.getText();
        String apellido = Apellido_Usuario.getText();
        String correo = Correo_Usuario.getText();
        String contrasena = Contra_Usu.getText();

        if (nombre.isEmpty() || usuario.isEmpty() || apellido.isEmpty() ||
                correo.isEmpty() || contrasena.isEmpty()) {
            System.out.println(" Todos los campos deben estar llenos.");
            return;
        }

        String url = "jdbc:postgresql://localhost:5432/Gestion_Tickets";
        String user = "postgres";
        String password = "pxrs";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            String sql = "INSERT INTO usuario (nombre, apellido, usuario, correo, contrasena) VALUES (?, ?, ?, ?, ?)";

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
            alert.setContentText("¡Usuario creado exitosamente!");
            alert.showAndWait();


            Sesion_Usuario.setUsuarioActual(usuario);


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Error al registrar el usuario.");


            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo registrar al usuario");
            alert.setContentText("Verifica que los datos estén correctos o si ya existe el usuario.");
            alert.showAndWait();
        }
    }


}//FIN DEL PROGRAMA
