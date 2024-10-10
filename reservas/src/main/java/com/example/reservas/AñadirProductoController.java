package com.example.reservas;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AñadirProductoController {
    @FXML
    private TextField nombreField;
    @FXML
    private TextField descripcionField;
    @FXML
    private TextField precioVentaField;
    @FXML
    private TextField unidadMedidaField;

    private GestionProductosController gestionProductosController;

    public void setGestionProductosController(GestionProductosController gestionProductosController) {
        this.gestionProductosController = gestionProductosController;
    }

    @FXML
    private void handleGuardarProducto() {
        try {
            String nombre = nombreField.getText();
            String descripcion = descripcionField.getText();
            double precioVenta = Double.parseDouble(precioVentaField.getText());
            String unidadMedida = unidadMedidaField.getText();

            Producto nuevoProducto = new Producto(nombre, descripcion, precioVenta, unidadMedida);

            new ProductoDAO().addProducto(nuevoProducto);
            gestionProductosController.getProductos().add(nuevoProducto); // Añadir el nuevo producto a la lista
            Stage stage = (Stage) nombreField.getScene().getWindow();
            stage.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Error de formato en uno de los campos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

