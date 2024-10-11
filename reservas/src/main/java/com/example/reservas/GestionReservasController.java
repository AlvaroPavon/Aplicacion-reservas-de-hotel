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
import java.time.LocalDate;

public class GestionReservasController {
    @FXML
    private TableView<Reserva> table;
    @FXML
    private TableColumn<Reserva, Integer> colCliente;
    @FXML
    private TableColumn<Reserva, Integer> colHabitacion;
    @FXML
    private TableColumn<Reserva, LocalDate> colFechaIngreso;
    @FXML
    private TableColumn<Reserva, LocalDate> colFechaSalida;
    @FXML
    private TableColumn<Reserva, Double> colCostoTotal;
    @FXML
    private Button agregarReserva;
    @FXML
    private Button eliminarReserva;
    @FXML
    private Button editarReserva;

    private ObservableList<Reserva> reservas;

    @FXML
    public void initialize() {
        reservas = FXCollections.observableArrayList();
        colCliente.setCellValueFactory(new PropertyValueFactory<>("idcliente"));
        colHabitacion.setCellValueFactory(new PropertyValueFactory<>("idhabitacion"));
        colFechaIngreso.setCellValueFactory(new PropertyValueFactory<>("fechaIngreso"));
        colFechaSalida.setCellValueFactory(new PropertyValueFactory<>("fechaSalida"));
        colCostoTotal.setCellValueFactory(new PropertyValueFactory<>("costoTotal"));
        table.setItems(reservas);

        try {
            reservas.setAll(new ReservaDAO().getAllReservas());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Reserva> getReservas() {
        return reservas;
    }
    /*Realizado por Alvaro Pavon Martinez */
    @FXML
    private void agregarReserva() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/reservas/nuevaReserva.fxml"));
            Parent root = loader.load();

            NuevaReservaController nuevaReservaController = loader.getController();
            nuevaReservaController.setGestionReservasController(this); // Pasar la referencia del controlador

            Stage stage = new Stage();
            stage.setTitle("Añadir Nueva Reserva");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void editarReserva() {
        Reserva selectedReserva = table.getSelectionModel().getSelectedItem();
        if (selectedReserva != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/reservas/editarReserva.fxml"));
                Parent root = loader.load();

                EditarReservaController editarReservaController = loader.getController();
                editarReservaController.setReserva(selectedReserva);
                editarReservaController.setGestionReservasController(this);

                Stage stage = new Stage();
                stage.setTitle("Editar Reserva");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void eliminarReserva() {
        Reserva selectedReserva = table.getSelectionModel().getSelectedItem();
        if (selectedReserva != null) {
            try {
                new ReservaDAO().deleteReserva(selectedReserva.getIdreserva());
                reservas.remove(selectedReserva);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

