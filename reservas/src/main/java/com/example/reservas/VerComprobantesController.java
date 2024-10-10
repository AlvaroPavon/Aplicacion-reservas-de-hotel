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
import java.time.LocalDate;
import java.util.List;

public class VerComprobantesController {
    @FXML
    private TableView<Comprobante> table;
    @FXML
    private TableColumn<Comprobante, Integer> colIdComprobante;
    @FXML
    private TableColumn<Comprobante, Integer> colIdReserva;
    @FXML
    private TableColumn<Comprobante, String> colTipoComprobante;
    @FXML
    private TableColumn<Comprobante, String> colNumComprobante;
    @FXML
    private TableColumn<Comprobante, Double> colIgv;
    @FXML
    private TableColumn<Comprobante, Double> colCostoConsumo;
    @FXML
    private TableColumn<Comprobante, Double> colCostoReserva;
    @FXML
    private TableColumn<Comprobante, LocalDate> colFechaEmision;
    @FXML
    private TableColumn<Comprobante, LocalDate> colFechaPago;
    @FXML
    private TableColumn<Comprobante, String> colEstado;

    private ObservableList<Comprobante> comprobantes;

    @FXML
    public void initialize() {
        comprobantes = FXCollections.observableArrayList();
        colIdComprobante.setCellValueFactory(new PropertyValueFactory<>("idcomprobante"));
        colIdReserva.setCellValueFactory(new PropertyValueFactory<>("idreserva"));
        colTipoComprobante.setCellValueFactory(new PropertyValueFactory<>("tipoComprobante"));
        colNumComprobante.setCellValueFactory(new PropertyValueFactory<>("numComprobante"));
        colIgv.setCellValueFactory(new PropertyValueFactory<>("igv"));
        colCostoConsumo.setCellValueFactory(new PropertyValueFactory<>("costoConsumo"));
        colCostoReserva.setCellValueFactory(new PropertyValueFactory<>("costoReserva"));
        colFechaEmision.setCellValueFactory(new PropertyValueFactory<>("fechaEmision"));
        colFechaPago.setCellValueFactory(new PropertyValueFactory<>("fechaPago"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        table.setItems(comprobantes);

        cargarComprobantes();
    }

    private void cargarComprobantes() {
        try {
            List<Comprobante> allComprobantes = ComprobanteFileManager.cargarComprobantes();
            comprobantes.setAll(allComprobantes);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleVerComprobante() {
        Comprobante selectedComprobante = table.getSelectionModel().getSelectedItem();
        if (selectedComprobante != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/reservas/verComprobante.fxml"));
                Parent root = loader.load();

                VerComprobanteController verComprobanteController = loader.getController();
                verComprobanteController.setComprobante(selectedComprobante);

                Stage stage = new Stage();
                stage.setTitle("Ver Comprobante");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

