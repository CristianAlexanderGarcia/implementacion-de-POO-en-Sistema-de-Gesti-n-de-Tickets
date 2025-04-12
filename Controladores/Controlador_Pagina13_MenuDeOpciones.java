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

public class Controlador_Pagina13_MenuDeOpciones implements Initializable {


    @FXML
    private Button GestionDeEstados;

    @FXML
    private Button GestorDeRoles;

    @FXML
    private Button GestorDeUsuarios;

    @FXML
    private Button GestorDepartamentos;

    @FXML
    private Button GestorFlujodeTabajo;

    @FXML
    private Button HistorialAdministrador;

    @FXML
    private Button VolverALinicio;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void GestionEstadosDeTi(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_tickets/Pagina19_MenuGestorDe_Tickets.fxml"));
            Pane secondWindow = loader.load();
            Scene secondScene = new Scene(secondWindow);
            Stage stage = (Stage) GestionDeEstados.getScene().getWindow();
            stage.setScene(secondScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void GestorDepartamental(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_tickets/Pagina14_MenuGestionDepartamental.fxml"));
            Pane secondWindow = loader.load();
            Scene secondScene = new Scene(secondWindow);
            Stage stage = (Stage) GestorDepartamentos.getScene().getWindow();
            stage.setScene(secondScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void GestorRoles(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_tickets/Pagina18_MenuGestionRols.fxml"));
            Pane secondWindow = loader.load();
            Scene secondScene = new Scene(secondWindow);
            Stage stage = (Stage) GestorDeRoles.getScene().getWindow();
            stage.setScene(secondScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    void GestorFlujo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_tickets/Pagina15_MenuFlujDeTrabajo.fxml"));
            Pane secondWindow = loader.load();
            Scene secondScene = new Scene(secondWindow);
            Stage stage = (Stage) GestorFlujodeTabajo.getScene().getWindow();
            stage.setScene(secondScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void HistorialAdministrador(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_tickets/Pagina16_HistorialAdministrador.fxml"));
            Pane secondWindow = loader.load();
            Scene secondScene = new Scene(secondWindow);
            Stage stage = (Stage) HistorialAdministrador.getScene().getWindow();
            stage.setScene(secondScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void LlevaAlGestorUsuarios(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_tickets/Pagina17_MenuGestionUsuarios.fxml"));
            Pane secondWindow = loader.load();
            Scene secondScene = new Scene(secondWindow);
            Stage stage = (Stage) GestorDeUsuarios.getScene().getWindow();
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
