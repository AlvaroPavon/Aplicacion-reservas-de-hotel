package com.example.reservas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class MainController {
    @FXML
    private TableView<ReservaExtendida> table;
    @FXML
    private TableColumn<ReservaExtendida, Integer> colId;
    @FXML
    private TableColumn<ReservaExtendida, String> colCliente;
    @FXML
    private TableColumn<ReservaExtendida, Integer> colHabitacion;
    @FXML
    private TableColumn<ReservaExtendida, LocalDate> colFechaIngreso;
    @FXML
    private TableColumn<ReservaExtendida, LocalDate> colFechaSalida;
    @FXML
    private TableColumn<ReservaExtendida, String> colEstado;

    private ObservableList<ReservaExtendida> reservas;

    @FXML
    public void initialize() {
        reservas = FXCollections.observableArrayList();
        colId.setCellValueFactory(new PropertyValueFactory<>("idreserva"));
        colCliente.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
        colHabitacion.setCellValueFactory(new PropertyValueFactory<>("idhabitacion"));
        colFechaIngreso.setCellValueFactory(new PropertyValueFactory<>("fechaIngreso"));
        colFechaSalida.setCellValueFactory(new PropertyValueFactory<>("fechaSalida"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        table.setItems(reservas);

        cargarReservasProximas();
        generarComprobantesAutomaticos();
    }

    private void cargarReservasProximas() {
        try {
            List<Reserva> todasReservas = new ReservaDAO().getAllReservas();
            LocalDate hoy = LocalDate.now();

            // Filtrar reservas próximas y eliminar las expiradas
            todasReservas.removeIf(reserva -> reserva.getFechaSalida().isBefore(hoy));

            // Ordenar por fecha de ingreso
            todasReservas.sort(Comparator.comparing(Reserva::getFechaIngreso));

            for (Reserva reserva : todasReservas) {
                Cliente cliente = new ClienteDAO().getClienteById(reserva.getIdcliente());
                String nombreCompleto = cliente.getNombre() + " " + cliente.getApellido() + " " + cliente.getAmaterno();
                reservas.add(new ReservaExtendida(reserva, nombreCompleto));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void generarComprobantesAutomaticos() {
        try {
            List<Reserva> todasReservas = new ReservaDAO().getAllReservas();
            LocalDate hoy = LocalDate.now();

            for (Reserva reserva : todasReservas) {
                if (reserva.getEstado().equals("Finalizada") && reserva.getFechaSalida().isBefore(hoy)) {
                    // Verificamos si ya existe un comprobante para esta reserva
                    if (!existeComprobante(reserva.getIdreserva())) {
                        Comprobante comprobante = new Comprobante(
                                reserva.getIdreserva(),
                                "Factura",
                                "F-" + reserva.getIdreserva(),
                                0.18,
                                0, // Supongamos que no hay consumo adicional
                                reserva.getCostoTotal(),
                                hoy,
                                hoy,
                                "Pagado"
                        );
                        ComprobanteFileManager.guardarComprobante(comprobante);
                    }
                }
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean existeComprobante(int idreserva) throws IOException, ClassNotFoundException {
        List<Comprobante> comprobantes = ComprobanteFileManager.cargarComprobantes();
        for (Comprobante comprobante : comprobantes) {
            if (comprobante.getIdreserva() == idreserva) {
                return true;
            }
        }
        return false;
    }

    @FXML
    private void handleGestionarClientes() {
        cargarVista("/com/example/reservas/gestionClientes.fxml", "Gestión de Clientes");
    }

    @FXML
    private void handleGestionarHabitaciones() {
        cargarVista("/com/example/reservas/gestionHabitaciones.fxml", "Gestión de Habitaciones");
    }

    @FXML
    private void handleGestionarReservas() {
        cargarVista("/com/example/reservas/gestionReservas.fxml", "Gestión de Reservas");
    }

    @FXML
    private void handleVerComprobantes() {
        cargarVista("/com/example/reservas/verComprobantes.fxml", "Ver Comprobantes");
    }


    private void cargarVista(String fxmlFile, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

