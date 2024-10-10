package com.example.reservas;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class NuevoClienteController {
    @FXML
    private TextField nombreField;
    @FXML
    private TextField apellidoField;
    @FXML
    private TextField amaternoField;
    @FXML
    private ChoiceBox<String> tipoDocumentoField;
    @FXML
    private TextField numeroDocumentoField;
    @FXML
    private TextField direccionField;
    @FXML
    private TextField telefonoField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField estadoField;
    @FXML
    private TextField codigoClienteField;

    private GestionClientesController gestionClientesController;

    public void setGestionClientesController(GestionClientesController gestionClientesController) {
        this.gestionClientesController = gestionClientesController;
    }

    @FXML
    private void handleGuardarCliente() {
        Cliente nuevoCliente = new Cliente(
                nombreField.getText(),
                apellidoField.getText(),
                amaternoField.getText(),
                tipoDocumentoField.getValue(),
                numeroDocumentoField.getText(),
                direccionField.getText(),
                telefonoField.getText(),
                emailField.getText(),
                estadoField.getText(),
                codigoClienteField.getText()
        );

        try {
            new ClienteDAO().addCliente(nuevoCliente);
            gestionClientesController.getClientes().add(nuevoCliente); // Añadir el nuevo cliente a la lista
            Stage stage = (Stage) nombreField.getScene().getWindow();
            stage.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

