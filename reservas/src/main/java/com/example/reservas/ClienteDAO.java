package com.example.reservas;/*Realizado por Alvaro Pavon Martinez */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    public List<Cliente> getAllClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM persona INNER JOIN cliente ON persona.idpersona = cliente.idpersona";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getString("nombre"),
                        rs.getString("apaterno"),
                        rs.getString("amaterno"),
                        rs.getString("tipo_documento"),
                        rs.getString("numero_documento"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("estado"),
                        rs.getString("codigo_cliente")
                );
                cliente.setIdpersona(rs.getInt("idpersona"));
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    public void addCliente(Cliente cliente) throws SQLException {
        String queryPersona = "INSERT INTO persona (nombre, apaterno, amaterno, tipo_documento, numero_documento, direccion, telefono, email, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String queryCliente = "INSERT INTO cliente (idpersona, codigo_cliente) VALUES (LAST_INSERT_ID(), ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmtPersona = conn.prepareStatement(queryPersona);
             PreparedStatement pstmtCliente = conn.prepareStatement(queryCliente)) {

            conn.setAutoCommit(false);

            pstmtPersona.setString(1, cliente.getNombre());
            pstmtPersona.setString(2, cliente.getApellido());
            pstmtPersona.setString(3, cliente.getAmaterno());
            pstmtPersona.setString(4, cliente.getTipoDocumento());
            pstmtPersona.setString(5, cliente.getNumeroDocumento());
            pstmtPersona.setString(6, cliente.getDireccion());
            pstmtPersona.setString(7, cliente.getTelefono());
            pstmtPersona.setString(8, cliente.getEmail());
            pstmtPersona.setString(9, cliente.getEstado());

            pstmtPersona.executeUpdate();

            pstmtCliente.setString(1, cliente.getCodigoCliente());

            pstmtCliente.executeUpdate();

            conn.commit();
        }
    }

    public void deleteCliente(int idpersona) throws SQLException {
        String updateReserva = "UPDATE reserva SET idcliente = NULL WHERE idcliente = ?";
        String deleteCliente = "DELETE FROM persona WHERE idpersona = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmtUpdate = conn.prepareStatement(updateReserva);
             PreparedStatement pstmtDelete = conn.prepareStatement(deleteCliente)) {

            conn.setAutoCommit(false);

            // Actualizar reservas para que idcliente sea NULL
            pstmtUpdate.setInt(1, idpersona);
            pstmtUpdate.executeUpdate();

            // Eliminar cliente de la tabla persona
            pstmtDelete.setInt(1, idpersona);
            pstmtDelete.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCliente(Cliente cliente) throws SQLException {
        String query = "UPDATE persona SET nombre = ?, apaterno = ?, amaterno = ?, tipo_documento = ?, numero_documento = ?, direccion = ?, telefono = ?, email = ?, estado = ? WHERE idpersona = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getApellido());
            pstmt.setString(3, cliente.getAmaterno());
            pstmt.setString(4, cliente.getTipoDocumento());
            pstmt.setString(5, cliente.getNumeroDocumento());
            pstmt.setString(6, cliente.getDireccion());
            pstmt.setString(7, cliente.getTelefono());
            pstmt.setString(8, cliente.getEmail());
            pstmt.setString(9, cliente.getEstado());
            pstmt.setInt(10, cliente.getIdpersona());

            pstmt.executeUpdate();
        }
    }
    /*Realizado por Alvaro Pavon Martinez */
    public Cliente getClienteById(int idpersona) throws SQLException {
        String query = "SELECT persona.idpersona, persona.nombre, persona.apaterno, persona.amaterno, persona.tipo_documento, persona.numero_documento, persona.direccion, persona.telefono, persona.email, persona.estado, cliente.codigo_cliente FROM persona INNER JOIN cliente ON persona.idpersona = cliente.idpersona WHERE persona.idpersona = ?";
        Cliente cliente = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, idpersona);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente(
                        rs.getString("nombre"),
                        rs.getString("apaterno"), // Ajustamos "apaterno" en vez de "apellido"
                        rs.getString("amaterno"),
                        rs.getString("tipo_documento"),
                        rs.getString("numero_documento"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("estado"),
                        rs.getString("codigo_cliente")
                );
                cliente.setIdpersona(rs.getInt("idpersona"));
            }
        }
        return cliente;
    }
    }
















/*Realizado por Alvaro Pavon Martinez */