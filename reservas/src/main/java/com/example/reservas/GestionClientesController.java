package com.example.reservas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class GestionClientesController {
    @FXML
    private TableView<Cliente> table;
    @FXML
    private TableColumn<Cliente, String> colNombre;
    @FXML
    private TableColumn<Cliente, String> colApellido;
    @FXML
    private TableColumn<Cliente, String> colEmail;
    @FXML
    private Button agregarCliente;
    @FXML
    private Button eliminarCliente;
    @FXML
    private Button editarCliente;

    private ObservableList<Cliente> clientes;

    @FXML
    public void initialize() {
        clientes = FXCollections.observableArrayList();
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        table.setItems(clientes);

        try {
            clientes.setAll(new ClienteDAO().getAllClientes());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Cliente> getClientes() {
        return clientes;
    }

    @FXML
    private void agregarCliente() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("nuevoCliente.fxml"));
            Parent root = loader.load();

            NuevoClienteController nuevoClienteController = loader.getController();
            nuevoClienteController.setGestionClientesController(this); // Pasar la referencia del controlador

            Stage stage = new Stage();
            stage.setTitle("Añadir Nuevo Cliente");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void eliminarCliente() {
        Cliente selectedCliente = table.getSelectionModel().getSelectedItem();
        if (selectedCliente != null) {
            try {
                new ClienteDAO().deleteCliente(selectedCliente.getIdpersona());
                clientes.remove(selectedCliente);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void editarCliente() {
        Cliente selectedCliente = table.getSelectionModel().getSelectedItem();
        if (selectedCliente != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("editarCliente.fxml"));
                Parent root = loader.load();

                EditarClienteController editarClienteController = loader.getController();
                editarClienteController.setCliente(selectedCliente);
                editarClienteController.setGestionClientesController(this); // Pasar la referencia del controlador

                Stage stage = new Stage();
                stage.setTitle("Editar Cliente");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


