package com.example.reservas;
/*Realizado por Alvaro Pavon Martinez */
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class EditarClienteController {
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

    private Cliente cliente;
    private GestionClientesController gestionClientesController;

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        nombreField.setText(cliente.getNombre());
        apellidoField.setText(cliente.getApellido());
        amaternoField.setText(cliente.getAmaterno());
        tipoDocumentoField.setValue(cliente.getTipoDocumento());
        numeroDocumentoField.setText(cliente.getNumeroDocumento());
        direccionField.setText(cliente.getDireccion());
        telefonoField.setText(cliente.getTelefono());
        emailField.setText(cliente.getEmail());
        estadoField.setText(cliente.getEstado());
        codigoClienteField.setText(cliente.getCodigoCliente());
    }

    public void setGestionClientesController(GestionClientesController gestionClientesController) {
        this.gestionClientesController = gestionClientesController;
    }

    @FXML
    private void handleGuardarCambios() {
        cliente.setNombre(nombreField.getText());
        cliente.setApellido(apellidoField.getText());
        cliente.setAmaterno(amaternoField.getText());
        cliente.setTipoDocumento(tipoDocumentoField.getValue());
        cliente.setNumeroDocumento(numeroDocumentoField.getText());
        cliente.setDireccion(direccionField.getText());
        cliente.setTelefono(telefonoField.getText());
        cliente.setEmail(emailField.getText());
        cliente.setEstado(estadoField.getText());
        cliente.setCodigoCliente(codigoClienteField.getText());

        try {
            new ClienteDAO().updateCliente(cliente);
            gestionClientesController.getClientes().set(gestionClientesController.getClientes().indexOf(cliente), cliente); // Actualizar la lista
            Stage stage = (Stage) nombreField.getScene().getWindow();
            stage.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



