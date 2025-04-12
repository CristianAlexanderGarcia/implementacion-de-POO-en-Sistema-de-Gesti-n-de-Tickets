package com.example.sistema_tickets.P;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Claseprincipal extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Claseprincipal.class.getResource("/com/example/sistema_tickets/Pagina1_MenuBienvenida.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 637);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}//FIN DEL PROGRAMA
