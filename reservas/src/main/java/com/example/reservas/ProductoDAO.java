package com.example.reservas;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    public List<Producto> getAllProductos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM producto";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio_venta"),
                        rs.getString("unidad_medida")
                );
                producto.setIdproducto(rs.getInt("idproducto"));
                productos.add(producto);
            }
        }
        return productos;
    }

    public void addProducto(Producto producto) throws SQLException {
        String query = "INSERT INTO producto (nombre, descripcion, precio_venta, unidad_medida) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, producto.getNombre());
            pstmt.setString(2, producto.getDescripcion());
            pstmt.setDouble(3, producto.getPrecioVenta());
            pstmt.setString(4, producto.getUnidadMedida());

            pstmt.executeUpdate();
        }
    }

    public void updateProducto(Producto producto) throws SQLException {
        String query = "UPDATE producto SET nombre = ?, descripcion = ?, precio_venta = ?, unidad_medida = ? WHERE idproducto = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, producto.getNombre());
            pstmt.setString(2, producto.getDescripcion());
            pstmt.setDouble(3, producto.getPrecioVenta());
            pstmt.setString(4, producto.getUnidadMedida());
            pstmt.setInt(5, producto.getIdproducto());

            pstmt.executeUpdate();
        }
    }
}



