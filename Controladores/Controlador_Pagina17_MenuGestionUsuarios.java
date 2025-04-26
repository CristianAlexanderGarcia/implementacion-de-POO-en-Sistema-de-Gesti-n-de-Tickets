package com.example.sistema_tickets.Controladores;

import com.example.sistema_tickets.Clases_Modelo.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Controlador_Pagina17_MenuGestionUsuarios implements Initializable {

    @FXML
    private Button RegresoAlmenu;

    @FXML
    private TextField Buscar;

    @FXML
    private Button Editar;

    @FXML
    private TableColumn<Usuario, String> ColumnaNombrePrincipal;

    @FXML
    private TableColumn<Usuario, String> ColumnaApellido;

    @FXML
    private TableColumn<Usuario, String> ColumnaCorreoUsuario;

    @FXML
    private TableColumn<Usuario, String> ColumnaNombreUsuario;

    @FXML
    private TableColumn<Usuario, String> ColumnaContrasena;

    @FXML
    private TableView<Usuario> tablaUsuarios;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ColumnaNombrePrincipal.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ColumnaApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        ColumnaCorreoUsuario.setCellValueFactory(new PropertyValueFactory<>("correo"));
        ColumnaNombreUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        ColumnaContrasena.setCellValueFactory(new PropertyValueFactory<>("contrasena"));


        cargarDatos();


        Buscar.textProperty().addListener((observable, oldValue, newValue) -> filtrarDatos(newValue));
    }

    private void cargarDatos() {
        String url = "jdbc:postgresql://localhost:5432/Gestion_Tickets";
        String user = "postgres";
        String password = "pxrs";

        ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList();

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            String sql = "SELECT * FROM Usuario";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Usuario usuario = new Usuario(
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("correo"),
                            rs.getString("usuario"),
                            rs.getString("contrasena")
                    );
                    listaUsuarios.add(usuario);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        tablaUsuarios.setItems(listaUsuarios);
    }

    @FXML
    void Buscar_Usuarios(ActionEvent event) {

        String keyword = Buscar.getText().trim();
        filtrarDatos(keyword);
    }

    private void filtrarDatos(String keyword) {
        ObservableList<Usuario> listaFiltrada = FXCollections.observableArrayList();

        for (Usuario usuario : tablaUsuarios.getItems()) {
            if (usuario.getNombre().toLowerCase().contains(keyword.toLowerCase()) ||
                    usuario.getApellido().toLowerCase().contains(keyword.toLowerCase()) ||
                    usuario.getCorreo_Electronico().toLowerCase().contains(keyword.toLowerCase()) ||
                    usuario.getNombre_de_Usuario().toLowerCase().contains(keyword.toLowerCase())) {
                listaFiltrada.add(usuario);
            }
        }

        tablaUsuarios.setItems(listaFiltrada);
    }

    @FXML
    void Regresa(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_tickets/Pagina13_MenuDeOpciones.fxml"));
            Pane previousWindow = loader.load();
            Scene previousScene = new Scene(previousWindow);
            Stage stage = (Stage) RegresoAlmenu.getScene().getWindow();
            stage.setScene(previousScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Editar_Datos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_tickets/Pagina17_2_Crud_Usuario.fxml"));
            Pane root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Gestión de Usuario");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}// FIN DEL PROGRAMA

