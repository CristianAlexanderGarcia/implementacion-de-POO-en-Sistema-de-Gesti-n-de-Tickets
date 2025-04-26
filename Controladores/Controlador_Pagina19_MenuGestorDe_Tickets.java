package com.example.sistema_tickets.Controladores;
import com.example.sistema_tickets.Clases_Modelo.Datos_Tickets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class Controlador_Pagina19_MenuGestorDe_Tickets implements Initializable {
    @FXML
    private TableColumn<?, ?> ColumnaCantidad;

    @FXML
    private TableColumn<?, ?> ColumnaCodigo;

    @FXML
    private TableColumn<?, ?> ColumnaDepartamento;

    @FXML
    private TableColumn<?, ?> ColumnaDescripcion;

    @FXML
    private TableColumn<?, ?> ColumnaEmpresa;

    @FXML
    private TableColumn<?, ?> ColumnaFechaCreacion;

    @FXML
    private TableColumn<?, ?> ColumnaFechaVencimiento;

    @FXML
    private TableColumn<?, ?> ColumnaNombre;

    @FXML
    private TableColumn<?, ?> ColumnaNotas;

    @FXML
    private TableColumn<?, ?> ColumnaPrioridad;

    @FXML
    private TableView<Datos_Tickets> tableViewTickets;

    @FXML
    private TextField Buscar;

    @FXML
    private Button Editar;

    @FXML
    private Button RegresaAlmenu;

    private final String url = "jdbc:postgresql://localhost:5432/Gestion_Tickets";
    private final String user = "postgres";
    private final String password = "pxrs";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ColumnaCodigo.setCellValueFactory(new PropertyValueFactory<>("Codigo"));
        ColumnaNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        ColumnaEmpresa.setCellValueFactory(new PropertyValueFactory<>("Empresa"));
        ColumnaPrioridad.setCellValueFactory(new PropertyValueFactory<>("Nivel_De_prioridad"));
        ColumnaDepartamento.setCellValueFactory(new PropertyValueFactory<>("Departamento"));
        ColumnaDescripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion_sobre_el_Ticket"));
        ColumnaCantidad.setCellValueFactory(new PropertyValueFactory<>("Cantidad_Disponible"));
        ColumnaFechaCreacion.setCellValueFactory(new PropertyValueFactory<>("Fecha_de_Creacion"));
        ColumnaFechaVencimiento.setCellValueFactory(new PropertyValueFactory<>("Fecha_de_Vencimiento"));
        ColumnaNotas.setCellValueFactory(new PropertyValueFactory<>("Notas"));


        cargarDatos();

        Buscar.textProperty().addListener((observable, oldValue, newValue) -> filtrarDatos(newValue));
    }

    private void cargarDatos() {
        String url = "jdbc:postgresql://localhost:5432/Gestion_Tickets";
        String user = "postgres";
        String password = "pxrs";

        ObservableList<Datos_Tickets> listaTickets = FXCollections.observableArrayList();

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM ticket";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {

                    int cantidad = rs.getInt("cantidad_disponible");
                    if (rs.wasNull()) {
                        cantidad = 0;
                    }


                    Date fechaCreacion = rs.getDate("fecha_creacion");
                    Date fechaVencimiento = rs.getDate("fecha_vencimiento");


                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String fechaCreacionStr = fechaCreacion != null ? sdf.format(fechaCreacion) : null;
                    String fechaVencimientoStr = fechaVencimiento != null ? sdf.format(fechaVencimiento) : null;


                    Datos_Tickets ticket = new Datos_Tickets(
                            rs.getString("departamento"),
                            rs.getString("empresa"),
                            rs.getString("prioridad"),
                            rs.getString("codigo_ticket"),
                            rs.getString("nombre_ticket"),
                            cantidad,
                            fechaCreacionStr,
                            fechaVencimientoStr,
                            rs.getString("notas"),
                            rs.getString("descripcion"),
                            rs.getString("estado")
                    );
                    listaTickets.add(ticket);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tableViewTickets.setItems(listaTickets);
    }


    @FXML
    void Buscar_Ticket(ActionEvent event) {

        String keyword = Buscar.getText().trim();


        filtrarDatos(keyword);
    }

    private void filtrarDatos(String keyword) {
        ObservableList<Datos_Tickets> listaFiltrada = FXCollections.observableArrayList();


        for (Datos_Tickets ticket : tableViewTickets.getItems()) {

            if (ticket.getCodigo().toLowerCase().contains(keyword.toLowerCase()) ||
                    ticket.getNombre().toLowerCase().contains(keyword.toLowerCase()) ||
                    ticket.getEmpresa().toLowerCase().contains(keyword.toLowerCase()) ||
                    ticket.getNivel_De_prioridad().toLowerCase().contains(keyword.toLowerCase()) ||
                    ticket.getDepartamento().toLowerCase().contains(keyword.toLowerCase()) ||
                    ticket.getDescripcion_sobre_el_Ticket().toLowerCase().contains(keyword.toLowerCase()) ||
                    String.valueOf(ticket.getCantidad_Disponible()).contains(keyword) ||
                    ticket.getFecha_de_Creacion().toLowerCase().contains(keyword.toLowerCase()) ||
                    ticket.getFecha_de_Vencimiento().toLowerCase().contains(keyword.toLowerCase())) {
                listaFiltrada.add(ticket);
            }
        }


        tableViewTickets.setItems(listaFiltrada);
    }

    @FXML
    void Regresa(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_tickets/Pagina13_MenuDeOpciones.fxml"));
            Pane previousWindow = loader.load();
            Scene previousScene = new Scene(previousWindow);

            Stage stage = (Stage) RegresaAlmenu.getScene().getWindow();
            stage.setScene(previousScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Editar_Datos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_tickets/Pagina19_2_Crud_Ticket.fxml"));
            Pane root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Gestión de Usuario");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}//FIN DEL PROGRAMA

