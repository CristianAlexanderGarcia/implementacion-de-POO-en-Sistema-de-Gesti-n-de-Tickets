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

public class Controlador_Pagina7_PantalladeGestion implements Initializable{


    @FXML
    private Button BotonRegreso;

    @FXML
    private Button Crear_tickets;

    @FXML
    private Button Mis_tickets;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void RegresarAlmenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_tickets/Pagina5_Acceso.fxml"));
            Pane previousWindow = loader.load();
            Scene previousScene = new Scene(previousWindow);

            Stage stage = (Stage) BotonRegreso.getScene().getWindow();
            stage.setScene(previousScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void Ver_misTickets(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_tickets/Pagina11_MisTickets_Creados.fxml"));
            Pane secondWindow = loader.load();
            Scene secondScene = new Scene(secondWindow);
            Stage stage = (Stage) Mis_tickets.getScene().getWindow();
            stage.setScene(secondScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void creacion_de_Ti(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_tickets/Pagina10_CreacionDe_Tickets.fxml"));
            Pane secondWindow = loader.load();
            Scene secondScene = new Scene(secondWindow);
            Stage stage = (Stage) Crear_tickets.getScene().getWindow();
            stage.setScene(secondScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}//FIN DEL PROGRAMA
