    package com.example.sistema_tickets.Controladores;

    import com.example.sistema_tickets.Clases_Modelo.Sesion_Usuario;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.fxml.Initializable;
    import javafx.scene.Scene;
    import javafx.scene.control.Button;
    import javafx.scene.control.Label;
    import javafx.stage.Stage;
    import java.net.URL;
    import java.sql.*;
    import java.util.ResourceBundle;

    public class Controlador_Pagina6_Perfil_Usuario implements Initializable {

        @FXML
        private Button BotonRegreso;
        @FXML
        private Label Monstar_contrasena;

        @FXML
        private Label Monstrar_Correo;

        @FXML
        private Label Mostar_Apellido;

        @FXML
        private Label Mostrar_Nombre_usuario;

        @FXML
        private Label mostrar_Nombre_Principal;

        private String usuarioActual;


        @Override
        public void initialize(URL location, ResourceBundle resources) {
            String usuario = Sesion_Usuario.getUsuarioActual();
            if (usuario != null && !usuario.isEmpty()) {
                usuarioActual = usuario;
                cargarDatosUsuario(); // Ya tenías este método
            }
        }


        @FXML
        public void RegresarAlMenu(ActionEvent event) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_tickets/Pagina5_Acceso.fxml"));
                Scene previousScene = new Scene(loader.load());
                Stage stage = (Stage) BotonRegreso.getScene().getWindow();
                stage.setScene(previousScene);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        public void setUsuarioActual(String usuario) {
            this.usuarioActual = usuario;
            cargarDatosUsuario();
        }
        private void cargarDatosUsuario() {
            String url = "jdbc:postgresql://localhost:5432/Gestion_Tickets";
            String user = "postgres";
            String password = "pxrs";

            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                String sql = "SELECT nombre, apellido, correo, contrasena, usuario FROM usuario WHERE usuario = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, usuarioActual);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    mostrar_Nombre_Principal.setText(rs.getString("nombre"));
                    Mostar_Apellido.setText(rs.getString("apellido"));
                    Monstrar_Correo.setText(rs.getString("correo"));
                    Monstar_contrasena.setText(rs.getString("contrasena"));
                    Mostrar_Nombre_usuario.setText(rs.getString("usuario"));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }//FIN DEL PROGRAMA
