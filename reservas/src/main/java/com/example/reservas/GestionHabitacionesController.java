package com.example.reservas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class GestionHabitacionesController {
    @FXML
    private TableView<Habitacion> table;
    @FXML
    private TableColumn<Habitacion, String> colNumero;
    @FXML
    private TableColumn<Habitacion, String> colPiso;
    @FXML
    private TableColumn<Habitacion, String> colDescripcion;
    @FXML
    private TableColumn<Habitacion, String> colCaracteristicas;
    @FXML
    private TableColumn<Habitacion, Double> colPrecioDiario;
    @FXML
    private TableColumn<Habitacion, String> colEstado;
    @FXML
    private TableColumn<Habitacion, String> colTipoHabitacion;
    @FXML
    private Button agregarHabitacion;
    @FXML
    private Button eliminarHabitacion;
    @FXML
    private Button editarHabitacion;

    private ObservableList<Habitacion> habitaciones;

    @FXML
    public void initialize() {
        habitaciones = FXCollections.observableArrayList();
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colPiso.setCellValueFactory(new PropertyValueFactory<>("piso"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colCaracteristicas.setCellValueFactory(new PropertyValueFactory<>("caracteristicas"));
        colPrecioDiario.setCellValueFactory(new PropertyValueFactory<>("precioDiario"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colTipoHabitacion.setCellValueFactory(new PropertyValueFactory<>("tipoHabitacion"));
        table.setItems(habitaciones);

        try {
            habitaciones.setAll(new HabitacionDAO().getAllHabitaciones());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    @FXML
    private void agregarHabitacion() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/reservas/nuevaHabitacion.fxml"));
            Parent root = loader.load();

            NuevaHabitacionController nuevaHabitacionController = loader.getController();
            nuevaHabitacionController.setGestionHabitacionesController(this);

            Stage stage = new Stage();
            stage.setTitle("Añadir Nueva Habitación");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void eliminarHabitacion() {
        Habitacion selectedHabitacion = table.getSelectionModel().getSelectedItem();
        if (selectedHabitacion != null) {
            try {
                new HabitacionDAO().deleteHabitacion(selectedHabitacion.getIdhabitacion());
                habitaciones.remove(selectedHabitacion);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /*Realizado por Alvaro Pavon Martinez */

    @FXML
    private void editarHabitacion() {
        Habitacion selectedHabitacion = table.getSelectionModel().getSelectedItem();
        if (selectedHabitacion != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/reservas/editarHabitaciones.fxml"));
                Parent root = loader.load();

                EditarHabitacionController editarHabitacionController = loader.getController();
                editarHabitacionController.setHabitacion(selectedHabitacion);
                editarHabitacionController.setGestionHabitacionesController(this);

                Stage stage = new Stage();
                stage.setTitle("Editar Habitación");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
