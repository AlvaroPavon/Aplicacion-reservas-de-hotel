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
import java.util.List;

public class GestionProductosController {
    @FXML
    private TableView<Producto> table;
    @FXML
    private TableColumn<Producto, Integer> colIdProducto;
    @FXML
    private TableColumn<Producto, String> colNombre;
    @FXML
    private TableColumn<Producto, String> colDescripcion;
    @FXML
    private TableColumn<Producto, Double> colPrecioVenta;
    @FXML
    private TableColumn<Producto, String> colUnidadMedida;

    private ObservableList<Producto> productos;

    @FXML
    public void initialize() {
        productos = FXCollections.observableArrayList();
        colIdProducto.setCellValueFactory(new PropertyValueFactory<>("idproducto"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colPrecioVenta.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
        colUnidadMedida.setCellValueFactory(new PropertyValueFactory<>("unidadMedida"));
        table.setItems(productos);

        cargarProductos();
    }

    private void cargarProductos() {
        try {
            List<Producto> todosProductos = new ProductoDAO().getAllProductos();
            productos.setAll(todosProductos);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Producto> getProductos() {
        return productos;
    }

    @FXML
    private void handleAgregarProducto() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/reservas/agregarProducto.fxml"));
            Parent root = loader.load();

            AñadirProductoController agregarProductoController = loader.getController();
            agregarProductoController.setGestionProductosController(this);

            Stage stage = new Stage();
            stage.setTitle("Añadir Producto");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleEditarProducto() {
        Producto selectedProducto = table.getSelectionModel().getSelectedItem();
        if (selectedProducto != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/reservas/editarProducto.fxml"));
                Parent root = loader.load();

                EditarProductoController editarProductoController = loader.getController();
                editarProductoController.setProducto(selectedProducto);
                editarProductoController.setGestionProductosController(this);

                Stage stage = new Stage();
                stage.setTitle("Editar Producto");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


