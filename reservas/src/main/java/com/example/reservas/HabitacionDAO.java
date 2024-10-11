package com.example.reservas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDAO {
    public List<Habitacion> getAllHabitaciones() throws SQLException {
        List<Habitacion> habitaciones = new ArrayList<>();
        String query = "SELECT * FROM habitacion";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Habitacion habitacion = new Habitacion(
                        rs.getString("numero"),
                        rs.getString("piso"),
                        rs.getString("descripcion"),
                        rs.getString("caracteristicas"),
                        rs.getDouble("precio_diario"),
                        rs.getString("estado"),
                        rs.getString("tipo_habitacion")
                );
                habitacion.setIdhabitacion(rs.getInt("idhabitacion"));
                habitaciones.add(habitacion);
            }
        }
        return habitaciones;
    }

    public void addHabitacion(Habitacion habitacion) throws SQLException {
        String query = "INSERT INTO habitacion (numero, piso, descripcion, caracteristicas, precio_diario, estado, tipo_habitacion) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            /*Realizado por Alvaro Pavon Martinez */
            pstmt.setString(1, habitacion.getNumero());
            pstmt.setString(2, habitacion.getPiso());
            pstmt.setString(3, habitacion.getDescripcion());
            pstmt.setString(4, habitacion.getCaracteristicas());
            pstmt.setDouble(5, habitacion.getPrecioDiario());
            pstmt.setString(6, habitacion.getEstado());
            pstmt.setString(7, habitacion.getTipoHabitacion());

            pstmt.executeUpdate();
        }
    }

    public void deleteHabitacion(int idhabitacion) throws SQLException {
        String updateReserva = "UPDATE reserva SET idhabitacion = NULL WHERE idhabitacion = ?";
        String deleteHabitacion = "DELETE FROM habitacion WHERE idhabitacion = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmtUpdate = conn.prepareStatement(updateReserva);
             PreparedStatement pstmtDelete = conn.prepareStatement(deleteHabitacion)) {

            conn.setAutoCommit(false);

            // Actualizar reservas para que idhabitacion sea NULL
            pstmtUpdate.setInt(1, idhabitacion);
            pstmtUpdate.executeUpdate();

            // Eliminar habitación de la tabla habitacion
            pstmtDelete.setInt(1, idhabitacion);
            pstmtDelete.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateHabitacion(Habitacion habitacion) throws SQLException {
        String updateReserva = "UPDATE reserva SET numero_habitacion = (SELECT numero FROM habitacion WHERE idhabitacion = ?) WHERE idhabitacion = ?";
        String query = "UPDATE habitacion SET numero = ?, piso = ?, descripcion = ?, caracteristicas = ?, precio_diario = ?, estado = ?, tipo_habitacion = ? WHERE idhabitacion = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmtUpdate = conn.prepareStatement(updateReserva);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            conn.setAutoCommit(false);

            // Depuración antes de actualizar reservas
            System.out.println("Actualizando reservas para la habitación: " + habitacion.getIdhabitacion());

            // Actualizar reservas para que mantengan el número de la habitación antes de la modificación
            pstmtUpdate.setInt(1, habitacion.getIdhabitacion());
            pstmtUpdate.setInt(2, habitacion.getIdhabitacion());
            pstmtUpdate.executeUpdate();

            // Depuración antes de actualizar la habitación
            System.out.println("Actualizando habitación: " + habitacion.getIdhabitacion());

            // Actualizar los datos de la habitación
            pstmt.setString(1, habitacion.getNumero());
            pstmt.setString(2, habitacion.getPiso());
            pstmt.setString(3, habitacion.getDescripcion());
            pstmt.setString(4, habitacion.getCaracteristicas());
            pstmt.setDouble(5, habitacion.getPrecioDiario());
            pstmt.setString(6, habitacion.getEstado());
            pstmt.setString(7, habitacion.getTipoHabitacion());
            pstmt.setInt(8, habitacion.getIdhabitacion());

            pstmt.executeUpdate();

            conn.commit();

            // Depuración después de actualizar la habitación
            System.out.println("Habitación actualizada exitosamente");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Habitacion> getHabitacionesDisponibles() throws SQLException {
        List<Habitacion> habitaciones = new ArrayList<>();
        String query = "SELECT * FROM habitacion WHERE estado = 'Disponible'";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Habitacion habitacion = new Habitacion(
                        rs.getString("numero"),
                        rs.getString("piso"),
                        rs.getString("descripcion"),
                        rs.getString("caracteristicas"),
                        rs.getDouble("precio_diario"),
                        rs.getString("estado"),
                        rs.getString("tipo_habitacion")
                );
                habitacion.setIdhabitacion(rs.getInt("idhabitacion"));
                habitaciones.add(habitacion);
            }
        }
        return habitaciones;
    }


}
