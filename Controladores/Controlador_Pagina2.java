package com.example.sistema_tickets.Controladores;

import com.example.sistema_tickets.Clases_Modelo.IdiomaS_Global;
import javafx.beans.binding.Bindings;
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

public class Controlador_Pagina2 implements Initializable {

    @FXML
    private Button accederBoton;

    @FXML
    private Button idiomas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accederBoton.textProperty().bind(Bindings.format(resources.getString("boton.acceder")));
        idiomas.textProperty().bind(Bindings.format(resources.getString("boton.idioma")));
    }


    private void actualizarTextos(ResourceBundle resources) {
        accederBoton.setText(resources.getString("boton.acceder"));
        idiomas.setText(resources.getString("boton.idioma"));
    }

    @FXML
    void TerceraVentana(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_tickets/Pagina3_MenuPrincipal.fxml"), IdiomaS_Global.getBundle());
            Pane secondWindow = loader.load();
            Scene secondScene = new Scene(secondWindow);
            Stage stage = (Stage) accederBoton.getScene().getWindow();
            stage.setScene(secondScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Cambiar_idioma(ActionEvent event) {

        if (IdiomaS_Global.getIdiomaActual().getLanguage().equals("es")) {
            IdiomaS_Global.cambiarIdioma(new Locale("en", "US"));
        } else {
            IdiomaS_Global.cambiarIdioma(new Locale("es", "ES"));
        }

        recargarVentana("/com/example/sistema_tickets/Pagina2_LogoAcceso.fxml");
    }

    private void recargarVentana(String rutaFXML) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML), IdiomaS_Global.getBundle());
            Pane root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) idiomas.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}//FIN DEL PROGRAMA


