package com.example.reservas;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class EditarHabitacionController {
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

    private Habitacion habitacion;
    private GestionHabitacionesController gestionHabitacionesController;

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
        numeroField.setText(habitacion.getNumero());
        pisoField.setText(habitacion.getPiso());
        descripcionField.setText(habitacion.getDescripcion());
        caracteristicasField.setText(habitacion.getCaracteristicas());
        precioDiarioField.setText(String.valueOf(habitacion.getPrecioDiario()));
        estadoField.setValue(habitacion.getEstado());
        tipoHabitacionField.setText(habitacion.getTipoHabitacion());
    }

    public void setGestionHabitacionesController(GestionHabitacionesController gestionHabitacionesController) {
        this.gestionHabitacionesController = gestionHabitacionesController;
    }

    @FXML
    private void handleGuardarCambios() {
        try {
            // Depuración antes de realizar cambios
            System.out.println("Guardando cambios para la habitación: " + habitacion.getIdhabitacion());

            habitacion.setNumero(numeroField.getText());
            habitacion.setPiso(pisoField.getText());
            habitacion.setDescripcion(descripcionField.getText());
            habitacion.setCaracteristicas(caracteristicasField.getText());
            habitacion.setPrecioDiario(Double.parseDouble(precioDiarioField.getText()));
            habitacion.setEstado(estadoField.getValue());
            habitacion.setTipoHabitacion(tipoHabitacionField.getText());

            new HabitacionDAO().updateHabitacion(habitacion);

            // Depuración después de realizar cambios
            System.out.println("Cambios guardados exitosamente");

            gestionHabitacionesController.getHabitaciones().set(
                    gestionHabitacionesController.getHabitaciones().indexOf(habitacion), habitacion); // Actualizar la lista

            // Cerrar la ventana después de guardar
            Stage stage = (Stage) numeroField.getScene().getWindow();
            stage.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // Manejo de error si el precio diario no es un número válido
            System.out.println("Error de formato en el campo precio diario: " + precioDiarioField.getText());
            e.printStackTrace();
        }
    }
}
