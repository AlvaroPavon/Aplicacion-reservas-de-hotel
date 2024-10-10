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

public class EditarReservaController {
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

    private Reserva reserva;
    private GestionReservasController gestionReservasController;

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
        cargarClientes();
        cargarHabitacionesDisponibles();
        cargarEstados();
        cargarTiposReserva();

        clienteChoiceBox.setValue(getClienteById(reserva.getIdcliente()));
        habitacionChoiceBox.setValue(getHabitacionById(reserva.getIdhabitacion()));
        fechaReservaField.setValue(reserva.getFechaReserva());
        fechaIngresoField.setValue(reserva.getFechaIngreso());
        fechaSalidaField.setValue(reserva.getFechaSalida());
        costoTotalField.setText(String.valueOf(reserva.getCostoTotal()));
        observacionField.setText(reserva.getObservacion());
        estadoField.setValue(reserva.getEstado());
        tipoReservaField.setValue(reserva.getTipoReserva());
        if (habitacionChoiceBox.getValue() != null) {
            double precio = habitacionChoiceBox.getValue().getPrecioDiario();
            precioHabitacionLabel.setText("Precio por noche: " + precio + " €");
        }
    }

    public void setGestionReservasController(GestionReservasController gestionReservasController) {
        this.gestionReservasController = gestionReservasController;
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

    private Cliente getClienteById(int idCliente) {
        for (Cliente cliente : clienteChoiceBox.getItems()) {
            if (cliente.getIdpersona() == idCliente) {
                return cliente;
            }
        }
        return null;
    }

    private Habitacion getHabitacionById(int idHabitacion) {
        for (Habitacion habitacion : habitacionChoiceBox.getItems()) {
            if (habitacion.getIdhabitacion() == idHabitacion) {
                return habitacion;
            }
        }
        return null;
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
    private void handleGuardarCambios() {
        try {
            reserva.setIdcliente(clienteChoiceBox.getValue().getIdpersona());
            reserva.setIdhabitacion(habitacionChoiceBox.getValue().getIdhabitacion());
            reserva.setFechaReserva(fechaReservaField.getValue());
            reserva.setFechaIngreso(fechaIngresoField.getValue());
            reserva.setFechaSalida(fechaSalidaField.getValue());
            reserva.setCostoTotal(Double.parseDouble(costoTotalField.getText()));
            reserva.setObservacion(observacionField.getText());
            reserva.setEstado(estadoField.getValue());
            reserva.setTipoReserva(tipoReservaField.getValue());

            new ReservaDAO().updateReserva(reserva);

            gestionReservasController.getReservas().set(
                    gestionReservasController.getReservas().indexOf(reserva), reserva); // Actualizar la lista

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
            double precio = habitacionChoiceBox.getValue().getPrecioDiario();
            precioHabitacionLabel.setText("Precio por noche: " + precio + " €");
        }
    }
}
