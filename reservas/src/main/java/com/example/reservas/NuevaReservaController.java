package com.example.reservas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class NuevaReservaController {
    @FXML
    private ChoiceBox<Cliente> clienteChoiceBox;
    @FXML
    private ChoiceBox<Habitacion> habitacionChoiceBox;
    @FXML
    private DatePicker fechaReservaField;
    @FXML
    private DatePicker fechaIngresoField;
    @FXML
    private DatePicker fechaSalidaField;
    @FXML
    private TextField costoTotalField;
    @FXML
    private TextField observacionField;
    @FXML
    private ChoiceBox<String> estadoField;
    @FXML
    private ChoiceBox<String> tipoReservaField;
    @FXML
    private Label precioHabitacionLabel;

    private GestionReservasController gestionReservasController;

    public void setGestionReservasController(GestionReservasController gestionReservasController) {
        this.gestionReservasController = gestionReservasController;
        cargarClientes();
        cargarHabitacionesDisponibles();
        cargarEstados();
        cargarTiposReserva();
    }

    private void cargarClientes() {
        try {
            List<Cliente> clientes = new ClienteDAO().getAllClientes();
            ObservableList<Cliente> clientesObservableList = FXCollections.observableArrayList(clientes);
            clienteChoiceBox.setItems(clientesObservableList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void cargarHabitacionesDisponibles() {
        try {
            List<Habitacion> habitacionesDisponibles = new HabitacionDAO().getHabitacionesDisponibles();
            ObservableList<Habitacion> habitacionesObservableList = FXCollections.observableArrayList(habitacionesDisponibles);
            habitacionChoiceBox.setItems(habitacionesObservableList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void cargarEstados() {
        ObservableList<String> estados = FXCollections.observableArrayList("Reservada", "Cancelada", "Finalizada");
        estadoField.setItems(estados);
    }

    private void cargarTiposReserva() {
        ObservableList<String> tiposReserva = FXCollections.observableArrayList("Individual", "Grupal", "Corporativa");
        tipoReservaField.setItems(tiposReserva);
    }

    private void calcularCostoTotal() {
        if (fechaIngresoField.getValue() != null && fechaSalidaField.getValue() != null && habitacionChoiceBox.getValue() != null) {
            long dias = ChronoUnit.DAYS.between(fechaIngresoField.getValue(), fechaSalidaField.getValue());
            double costoHabitacion = habitacionChoiceBox.getValue().getPrecioDiario(); // Usando precioDiario
            double costoTotal = costoHabitacion * dias;
            costoTotalField.setText(String.valueOf(costoTotal));
        }
    }

    @FXML
    private void handleGuardarReserva() {
        try {
            if (clienteChoiceBox.getValue() == null || habitacionChoiceBox.getValue() == null || fechaReservaField.getValue() == null || fechaIngresoField.getValue() == null || fechaSalidaField.getValue() == null || costoTotalField.getText().isEmpty() || estadoField.getValue() == null || tipoReservaField.getValue() == null) {
                System.out.println("Por favor, complete todos los campos.");
                return;
            }

            Reserva nuevaReserva = new Reserva(
                    habitacionChoiceBox.getValue().getIdhabitacion(),
                    clienteChoiceBox.getValue().getIdpersona(),
                    tipoReservaField.getValue(),
                    fechaReservaField.getValue(),
                    fechaIngresoField.getValue(),
                    fechaSalidaField.getValue(),
                    Double.parseDouble(costoTotalField.getText()),
                    observacionField.getText(),
                    estadoField.getValue()
            );

            new ReservaDAO().addReserva(nuevaReserva);
            gestionReservasController.getReservas().add(nuevaReserva); // Añadir la nueva reserva a la lista
            Stage stage = (Stage) clienteChoiceBox.getScene().getWindow();
            stage.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Error de formato en uno de los campos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleFechaIngresoChange() {
        calcularCostoTotal();
    }

    @FXML
    private void handleFechaSalidaChange() {
        calcularCostoTotal();
    }

    @FXML
    private void handleHabitacionChange() {
        calcularCostoTotal();
        if (habitacionChoiceBox.getValue() != null) {
            precioHabitacionLabel.setText("Precio por día: " + habitacionChoiceBox.getValue().getPrecioDiario());
        }
    }
}
