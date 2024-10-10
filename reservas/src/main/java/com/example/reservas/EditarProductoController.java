package com.example.reservas;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class EditarProductoController {
    @FXML
    private TextField nombreField;
    @FXML
    private TextField descripcionField;
    @FXML
    private TextField precioVentaField;
    @FXML
    private TextField unidadMedidaField;

    private Producto producto;
    private GestionProductosController gestionProductosController;

    public void setProducto(Producto producto) {
        this.producto = producto;
        nombreField.setText(producto.getNombre());
        descripcionField.setText(producto.getDescripcion());
        precioVentaField.setText(String.valueOf(producto.getPrecioVenta()));
        unidadMedidaField.setText(producto.getUnidadMedida());
    }

    public void setGestionProductosController(GestionProductosController gestionProductosController) {
        this.gestionProductosController = gestionProductosController;
    }

    @FXML
    private void handleGuardarCambios() {
        try {
            producto.setNombre(nombreField.getText());
            producto.setDescripcion(descripcionField.getText());
            producto.setPrecioVenta(Double.parseDouble(precioVentaField.getText()));
            producto.setUnidadMedida(unidadMedidaField.getText());

            new ProductoDAO().updateProducto(producto);

            gestionProductosController.getProductos().set(
                    gestionProductosController.getProductos().indexOf(producto), producto); // Actualizar la lista

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

