package com.example.reservas;
/*Realizado por Alvaro Pavon Martinez */
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ComprobanteDAO {
    public List<Comprobante> getAllComprobantes() throws SQLException {
        List<Comprobante> comprobantes = new ArrayList<>();
        String query = "SELECT * FROM comprobante";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Comprobante comprobante = new Comprobante(
                        rs.getInt("idreserva"),
                        rs.getString("tipo_comprobante"),
                        rs.getString("num_comprobante"),
                        rs.getDouble("igv"),
                        rs.getDouble("costo_consumo"),
                        rs.getDouble("costo_reserva"),
                        rs.getDate("fecha_emision").toLocalDate(),
                        rs.getDate("fecha_pago").toLocalDate(),
                        rs.getString("estado")
                );
                comprobante.setIdcomprobante(rs.getInt("idcomprobante"));
                comprobantes.add(comprobante);
            }
        }
        return comprobantes;
    }

    public void addComprobante(Comprobante comprobante) throws SQLException {
        String query = "INSERT INTO comprobante (idreserva, tipo_comprobante, num_comprobante, igv, costo_consumo, costo_reserva, fecha_emision, fecha_pago, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, comprobante.getIdreserva());
            pstmt.setString(2, comprobante.getTipoComprobante());
            pstmt.setString(3, comprobante.getNumComprobante());
            pstmt.setDouble(4, comprobante.getIgv());
            pstmt.setDouble(5, comprobante.getCostoConsumo());
            pstmt.setDouble(6, comprobante.getCostoReserva());
            pstmt.setDate(7, Date.valueOf(comprobante.getFechaEmision()));
            pstmt.setDate(8, Date.valueOf(comprobante.getFechaPago()));
            pstmt.setString(9, comprobante.getEstado());

            pstmt.executeUpdate();
        }
    }
}










/*Realizado por Alvaro Pavon Martinez */