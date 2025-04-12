package com.example.sistema_tickets.Controladores;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controlador_Pagina5_Acceso implements Initializable{

    @FXML
    private Button BotonBusqueda;

    @FXML
    private Button BotonGestion;

    @FXML
    private Button BotonPerfil;

    @FXML
    private Button historialUsuario;

    @FXML
    private Button VolverALinicio;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void BuscarTi(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_tickets/Pagina9_MenuBusqueda_Usuario.fxml"));
            Pane secondWindow = loader.load();
            Scene secondScene = new Scene(secondWindow);
            Stage stage = (Stage) BotonBusqueda.getScene().getWindow();
            stage.setScene(secondScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void HistorialTi(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_tickets/Pagina8_MenuHistorial.fxml"));
            Pane secondWindow = loader.load();
            Scene secondScene = new Scene(secondWindow);
            Stage stage = (Stage) historialUsuario.getScene().getWindow();
            stage.setScene(secondScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void Lleva_Al_Gestor(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_tickets/Pagina7_PantalladeGestion.fxml"));
            Pane secondWindow = loader.load();
            Scene secondScene = new Scene(secondWindow);
            Stage stage = (Stage) BotonGestion.getScene().getWindow();
            stage.setScene(secondScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void PerfilAutor(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_tickets/Pagina6_Perfil_Usuario.fxml"));
            Pane secondWindow = loader.load();
            Scene secondScene = new Scene(secondWindow);
            Stage stage = (Stage) BotonPerfil.getScene().getWindow();
            stage.setScene(secondScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void NuevoComienzo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_tickets/Pagina3_MenuPrincipal.fxml"));
            Pane previousWindow = loader.load();
            Scene previousScene = new Scene(previousWindow);

            Stage stage = (Stage) VolverALinicio.getScene().getWindow();
            stage.setScene(previousScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}//FIN DEL PROGRAMA
