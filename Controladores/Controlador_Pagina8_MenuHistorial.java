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

public class Controlador_Pagina8_MenuHistorial implements Initializable{

    @FXML
    private Button RegresoAlMenu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void Regresar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_tickets/Pagina5_Acceso.fxml"));
            Pane previousWindow = loader.load();
            Scene previousScene = new Scene(previousWindow);

            Stage stage = (Stage) RegresoAlMenu.getScene().getWindow();
            stage.setScene(previousScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}//FIN DEL PROGRAMA
