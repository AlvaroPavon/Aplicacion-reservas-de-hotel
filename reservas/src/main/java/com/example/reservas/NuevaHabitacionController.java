package com.example.reservas;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
/*Realizado por Alvaro Pavon Martinez */
public class NuevaHabitacionController {
    @FXML
    private TextField numeroField;
    @FXML
    private TextField pisoField;
    @FXML
    private TextField descripcionField;
    @FXML
    private TextField caracteristicasField;
    @FXML
    private TextField precioDiarioField;
    @FXML
    private ChoiceBox<String> estadoField;
    @FXML
    private TextField tipoHabitacionField;

    private GestionHabitacionesController gestionHabitacionesController;

    public void setGestionHabitacionesController(GestionHabitacionesController gestionHabitacionesController) {
        this.gestionHabitacionesController = gestionHabitacionesController;
    }

    @FXML
    private void handleGuardarHabitacion() {
        Habitacion nuevaHabitacion = new Habitacion(
                numeroField.getText(),
                pisoField.getText(),
                descripcionField.getText(),
                caracteristicasField.getText(),
                Double.parseDouble(precioDiarioField.getText()),
                estadoField.getValue(),
                tipoHabitacionField.getText()
        );

        try {
            new HabitacionDAO().addHabitacion(nuevaHabitacion);
            gestionHabitacionesController.getHabitaciones().add(nuevaHabitacion); // Añadir la nueva habitación a la lista
            Stage stage = (Stage) numeroField.getScene().getWindow();
            stage.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

