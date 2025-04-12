package com.example.sistema_tickets.Controladores;


import com.example.sistema_tickets.Clases_Modelo.IdiomaS_Global;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class Controlador_Pagina1 implements Initializable {

    @FXML
    private Button botonAcceder;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }



    @FXML
    void ir_a_ventana2(ActionEvent event) {
        try {

            ResourceBundle bundle = ResourceBundle.getBundle("com.example.sistema_tickets.Idiomas.mensajes", new Locale("es", "ES"));


            System.out.println(bundle.getString("boton.acceder"));


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_tickets/Pagina2_LogoAcceso.fxml"), bundle);
            Pane secondWindow = loader.load();
            Scene secondScene = new Scene(secondWindow);


            Stage stage = (Stage) botonAcceder.getScene().getWindow();
            stage.setScene(secondScene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}//FIN DEL PROGRAMA