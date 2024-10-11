package com.example.reservas;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
/*Realizado por Alvaro Pavon Martinez */
public class VerComprobanteController {
    @FXML
    private Label idComprobanteLabel;
    @FXML
    private Label idReservaLabel;
    @FXML
    private Label tipoComprobanteLabel;
    @FXML
    private Label numComprobanteLabel;
    @FXML
    private Label igvLabel;
    @FXML
    private Label costoConsumoLabel;
    @FXML
    private Label costoReservaLabel;
    @FXML
    private Label fechaEmisionLabel;
    @FXML
    private Label fechaPagoLabel;
    @FXML
    private Label estadoLabel;

    public void setComprobante(Comprobante comprobante) {
        idComprobanteLabel.setText("ID Comprobante: " + comprobante.getIdcomprobante());
        idReservaLabel.setText("ID Reserva: " + comprobante.getIdreserva());
        tipoComprobanteLabel.setText("Tipo Comprobante: " + comprobante.getTipoComprobante());
        numComprobanteLabel.setText("Número Comprobante: " + comprobante.getNumComprobante());
        igvLabel.setText("IGV: " + comprobante.getIgv());
        costoConsumoLabel.setText("Costo Consumo: " + comprobante.getCostoConsumo());
        costoReservaLabel.setText("Costo Reserva: " + comprobante.getCostoReserva());
        fechaEmisionLabel.setText("Fecha Emisión: " + comprobante.getFechaEmision());
        fechaPagoLabel.setText("Fecha Pago: " + comprobante.getFechaPago());
        estadoLabel.setText("Estado: " + comprobante.getEstado());
    }
}

