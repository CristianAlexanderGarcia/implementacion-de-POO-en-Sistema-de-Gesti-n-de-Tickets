package com.example.sistema_tickets.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

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
    private TextField Nombre_Personal_del_usuario;

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
    void Registrar_Contrase(ActionEvent event) {


    }

    @FXML
    void Registrar_Nombre(ActionEvent event) {
        String nombre = Nombre_Principal_del_usuario.getText();

        String url = "jdbc:postgresql://localhost:5432/Gestion_Tickets";
        String user = "postgres";
        String password = "pxrs";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            String sql = "INSERT INTO usuarios (nombre, id_rol) VALUES (?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setInt(2, 3);

            stmt.executeUpdate();
            conn.close();

            System.out.println("Nombre guardado con éxito 🥰");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se pudo guardar el nombre 😢");
        }

    }

    @FXML
    void Registro_Apellido(ActionEvent event) {

    }

    @FXML
    void Registro_Nombre_Principal(ActionEvent event) {

    }

    @FXML
    void Registro_de_Correo(ActionEvent event) {

    }

    @FXML
    void Verifcar_usuario(ActionEvent event) {

    }

    @FXML
    void Registro_Asegurado(ActionEvent event) {

    }

}//FIN DEL PROGRAMA
