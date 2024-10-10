package com.example.reservas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    public List<Reserva> getAllReservas() throws SQLException {
        List<Reserva> reservas = new ArrayList<>();
        String query = "SELECT * FROM reserva";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Reserva reserva = new Reserva(
                        rs.getInt("idhabitacion"),
                        rs.getInt("idcliente"),
                        rs.getString("tipo_reserva"),
                        rs.getDate("fecha_reserva").toLocalDate(),
                        rs.getDate("fecha_ingreso").toLocalDate(),
                        rs.getDate("fecha_salida").toLocalDate(),
                        rs.getDouble("costo_total"),
                        rs.getString("observacion"),
                        rs.getString("estado")
                );
                reserva.setIdreserva(rs.getInt("idreserva"));
                reservas.add(reserva);
            }
        }
        return reservas;
    }

    public void addReserva(Reserva reserva) throws SQLException {
        String query = "INSERT INTO reserva (idhabitacion, idcliente, tipo_reserva, fecha_reserva, fecha_ingreso, fecha_salida, costo_total, observacion, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, reserva.getIdhabitacion());
            pstmt.setInt(2, reserva.getIdcliente());
            pstmt.setString(3, reserva.getTipoReserva());
            pstmt.setDate(4, Date.valueOf(reserva.getFechaReserva()));
            pstmt.setDate(5, Date.valueOf(reserva.getFechaIngreso()));
            pstmt.setDate(6, Date.valueOf(reserva.getFechaSalida()));
            pstmt.setDouble(7, reserva.getCostoTotal());
            pstmt.setString(8, reserva.getObservacion());
            pstmt.setString(9, reserva.getEstado());

            pstmt.executeUpdate();
        }
    }

        public void deleteReserva(int idreserva) throws SQLException {
        String query = "DELETE FROM reserva WHERE idreserva = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, idreserva);
            pstmt.executeUpdate();
        }
    }

    public void updateReserva(Reserva reserva) throws SQLException {
        String query = "UPDATE reserva SET idhabitacion = ?, idcliente = ?, tipo_reserva = ?, fecha_reserva = ?, fecha_ingreso = ?, fecha_salida = ?, costo_total = ?, observacion = ?, estado = ? WHERE idreserva = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Establecer todos los parámetros en el orden correcto
            pstmt.setInt(1, reserva.getIdhabitacion());
            pstmt.setInt(2, reserva.getIdcliente());
            pstmt.setString(3, reserva.getTipoReserva());
            pstmt.setDate(4, Date.valueOf(reserva.getFechaReserva()));
            pstmt.setDate(5, Date.valueOf(reserva.getFechaIngreso()));
            pstmt.setDate(6, Date.valueOf(reserva.getFechaSalida()));
            pstmt.setDouble(7, reserva.getCostoTotal());
            pstmt.setString(8, reserva.getObservacion());
            pstmt.setString(9, reserva.getEstado());
            pstmt.setInt(10, reserva.getIdreserva()); // Asegurarse de que el parámetro idreserva está al final

            pstmt.executeUpdate();
        }
    }

}

